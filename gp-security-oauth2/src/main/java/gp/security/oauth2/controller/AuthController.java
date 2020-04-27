package gp.security.oauth2.controller;

import com.gp.api.auth.AuthControllerApi;
import com.gp.framework.domain.auth.ext.AuthToken;
import com.gp.framework.domain.auth.request.LoginRequest;
import com.gp.framework.domain.auth.response.AuthCode;
import com.gp.framework.domain.auth.response.JwtResult;
import com.gp.framework.domain.auth.response.LoginResult;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.ResponseResult;
import gp.framework.utils.CookieUtil;
import gp.security.oauth2.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 码农界的小学生
 * @description:
 * @title: AuthController
 * @projectName xc-edu
 * @description: TODO
 * @date 2020/3/8 22:59
 */
@RestController
public class AuthController implements AuthControllerApi {
    //客户端id
    @Value("${auth.clientId}")
    private String clientId;
    //客户端密码
    @Value("${auth.clientSecret}")
    private String clientSecret;
    //管理员域名
    @Value("${auth.admin_cookieDomain}")
    private String admin_cookieDomain;
    //用户域名
    @Value("${auth.user_cookieDomain}")
    private String user_cookieDomain;
    //cookie存储时间
    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;
    @Autowired
    AuthService authService;
    @Override
    @PostMapping("/userlogin")
    public LoginResult login(@RequestBody LoginRequest loginRequest) {
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())){
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        //用户名
        String username = loginRequest.getUsername();
        //密码
        String password = loginRequest.getPassword();
        AuthToken authToken = authService.login(username,password,clientId,clientSecret);
        //将令牌存储到cookie中
        this.saveCookie(authToken);
        return new LoginResult(CommonCode.SUCCESS,authToken.getAccess_token());
    }

    @Override
    @PostMapping("/adminlogout")
    public ResponseResult adminlogout() {
        //从cookie中获取访问令牌
        String uid = this.getTokenFormCookie("admin");
        //删除redis中存储的令牌
        boolean b = authService.deleteToken("admin",uid);
        //删除cookie
        this.deleteAdminCookie();
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    @PostMapping("/userlogout")
    public ResponseResult userlogout() {
        //从cookie中获取访问令牌
        String uid = this.getTokenFormCookie("user");
        //删除redis中存储的令牌
        boolean b = authService.deleteToken("user",uid);
        //删除cookie
        this.deleteUserCookie();
        return new ResponseResult(CommonCode.SUCCESS);
    }


    @Override
    @GetMapping("/adminjwt")
    public JwtResult adminjwt() {
        //从cookie中获取访问令牌
        String uid = this.getTokenFormCookie("admin");
        if(StringUtils.isEmpty(uid)){
            return new JwtResult(CommonCode.FAIL,null);
        }
        //通过uid从redis中获取jwt
        AuthToken userToken = authService.getUserToken("admin",uid);
        if(userToken != null){
            //将jwt令牌返回给用户
            return new JwtResult(CommonCode.SUCCESS,userToken.getJwt_token());
        }
        return new JwtResult(CommonCode.FAIL,null);
    }

    @Override
    @GetMapping("/userjwt")
    public JwtResult userjwt() {
        //从cookie中获取访问令牌
        String uid = this.getTokenFormCookie("user");
        if(StringUtils.isEmpty(uid)){
            return new JwtResult(CommonCode.FAIL,null);
        }
        //通过uid从redis中获取jwt
        AuthToken userToken = authService.getUserToken("user",uid);
        if(userToken != null){
            //将jwt令牌返回给用户
            return new JwtResult(CommonCode.SUCCESS,userToken.getJwt_token());
        }
        return new JwtResult(CommonCode.FAIL,null);
    }

    //从cookie中获取访问令牌
    private String getTokenFormCookie(String type){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(type.equals("admin")){
            Map<String, String> map = CookieUtil.readCookie(request, "uid");
            if(map != null && map.get("uid") != null){
                return map.get("uid");
            }
        }else if(type.equals("user")){
            Map<String, String> map = CookieUtil.readCookie(request, "user_uid");
            if(map != null && map.get("user_uid") != null){
                return map.get("user_uid");
            }
        }
        return null;
    }

    /**
     * 将令牌存储到cookie
     * @param
     */
    private void saveCookie(AuthToken authToken){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if(authToken.getUser_type().equals("管理员")){
            CookieUtil.addCookie(response,admin_cookieDomain,"/","uid",authToken.getAccess_token(),cookieMaxAge,false);
        }else {
            CookieUtil.addCookie(response,user_cookieDomain,"/","user_uid",authToken.getAccess_token(),cookieMaxAge,false);
        }
    }
    /**
     * 删除管理员cookie
     */
    private void deleteAdminCookie(){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //addCookie(HttpServletResponse response,String domain,String path, String name,
        //                                 String value, int maxAge,boolean httpOnly)
        CookieUtil.addCookie(response,admin_cookieDomain,"/","uid","",0,false);
    }
    /**
     * 删除用户cookie
     */
    private void deleteUserCookie(){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //addCookie(HttpServletResponse response,String domain,String path, String name,
        //                                 String value, int maxAge,boolean httpOnly)
        CookieUtil.addCookie(response,user_cookieDomain,"/","user_uid","",0,false);
    }
}
