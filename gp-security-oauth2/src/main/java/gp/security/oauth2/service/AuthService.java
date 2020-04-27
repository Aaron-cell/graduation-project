package gp.security.oauth2.service;

import com.alibaba.fastjson.JSON;
import com.gp.framework.client.gpServiceList;
import com.gp.framework.domain.auth.ext.AuthToken;
import com.gp.framework.domain.auth.response.AuthCode;
import com.gp.framework.domain.user.ext.UserExt;
import com.gp.framework.exception.ExceptionCast;
import gp.security.oauth2.client.UserClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 码农界的小学生
 * @description:
 * @title: AuthService
 * @projectName xc-edu
 * @description: TODO
 * @date 2020/3/8 23:01
 */
@Service
public class AuthService {
    @Value("${auth.tokenValiditySeconds}")
    private long tokenValiditySeconds;
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    UserClient userClient;
    /**
     * 登录 申请令牌  将令牌存储到redis
     * @param username
     * @param password
     * @param clientId
     * @param clientSecret
     * @return
     */
    public AuthToken login(String username, String password, String clientId, String clientSecret) {
        //申请令牌
        AuthToken authToken = this.applyToken(username, password, clientId, clientSecret);
        if(authToken == null){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLYTOKEN_FAIL);
        }
        //查询用户类型
        UserExt userExt = userClient.findUserExtByUsername(username);
        authToken.setUser_type(userExt.getRole_name());
        //将令牌存储redis
//        String access_token = authToken.getAccess_token();
//        String toJSONString = JSON.toJSONString(authToken);
        boolean result = this.saveToken(authToken, tokenValiditySeconds);
        if(!result){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_TOKEN_SAVEFAIL);
        }
        return authToken;
    }

    //申请令牌
    private AuthToken applyToken(String username, String password, String clientId, String clientSecret){
        //从eureka中获取认证服务的地址（因为security在认证服务中）
        //从eureka中获取一个认证服务实例
        ServiceInstance serviceInstance = loadBalancerClient.choose(gpServiceList.GP_SECURITY_OAUTH2);
        URI uri = serviceInstance.getUri();//url即http://ip:port
        //令牌申请地址 http://localhost:30100/auth/oauth/token
        String authURL = uri+"/auth/oauth/token";
        //定义header
        MultiValueMap<String,String> header = new LinkedMultiValueMap<>();
        //Basic+空格+以base64格式编码的用户名和密码
        String basic = this.httpBasic(clientId, clientSecret);
        header.add("Authorization",basic);
        //定义Body
        MultiValueMap<String,String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","password");
        body.add("username",username);
        body.add("password",password);
        //定义HttpEntity
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        //当密码等一系列错误 security会抛出错误 400 401 为了拿到返回信息
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if(response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401){
                    super.handleError(response);
                }
            }
        });
        ResponseEntity<Map> exchange = restTemplate.exchange(authURL, HttpMethod.POST, httpEntity, Map.class);
        Map bodyMap = exchange.getBody();
        if(bodyMap == null || bodyMap.get("access_token") == null ||
                bodyMap.get("refresh_token") == null ||
                bodyMap.get("jti") == null ){
            //获取security返回的错误信息
            String error_description = (String) bodyMap.get("error_description");
            if(StringUtils.isNotEmpty(error_description)){
                if(error_description.equals("坏的凭证")){
                    ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
                }else if(error_description.indexOf("UserDetailsService returned null")>=0){
                    ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_NOTEXISTS);
                }
            }
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLYTOKEN_FAIL);
        }
        AuthToken authToken = new AuthToken();
        authToken.setAccess_token((String) bodyMap.get("jti"));
        authToken.setRefresh_token((String) bodyMap.get("refresh_token"));
        authToken.setJwt_token((String) bodyMap.get("access_token"));
        return authToken;
    }

    //获取httpBasic认证所需base64串
    private String httpBasic(String clientId, String clientSecret){
        String s = clientId+":"+clientSecret;
        byte[] bytes = Base64Utils.encode(s.getBytes());
        return "Basic "+new String(bytes);
    }

    /**
     * 将令牌存储到redis
     * @param t 时间
     * @return
     */
    private boolean saveToken(AuthToken authToken, long t){
        String key;
        if(authToken.getUser_type().equals("管理员")){
            //定义key
            key = "admin_token:"+authToken.getAccess_token();
        }else{
            //定义key
            key = "user_token:"+authToken.getAccess_token();
        }
        String content = JSON.toJSONString(authToken);
        //存储数据
        stringRedisTemplate.boundValueOps(key).set(content,t, TimeUnit.SECONDS);
        Long expire = stringRedisTemplate.getExpire(key,TimeUnit.SECONDS);
        return expire > 0;
    }
    /**
     * 将令牌从redis中删除
     * @param access_token jti
     * @return
     */
    public boolean deleteToken(String s, String access_token){
        //定义key
        String key = s+"_token:"+access_token;
        //删除数据
        stringRedisTemplate.delete(key);
        Long expire = stringRedisTemplate.getExpire(key,TimeUnit.SECONDS);
        return true;
    }

    /**
     * 从redis中查询令牌
     * @param token
     * @return
     */
    public AuthToken getUserToken(String s,String token){
        String userToken = s+"_token:"+token;
        String userTokenString = stringRedisTemplate.opsForValue().get(userToken);
        if(StringUtils.isEmpty(userTokenString)){
            return null;
        }
        try{
            AuthToken authToken = JSON.parseObject(userTokenString,AuthToken.class);
            return authToken;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
