package com.gp.cms_manager.sms.service;

import com.alibaba.fastjson.JSON;
import com.gp.cms_manager.config.RabbitmqConfig;
import com.gp.cms_manager.dao.UserRepository;
import com.gp.framework.domain.user.User;
import com.gp.framework.domain.user.response.UserCode;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.ResponseResult;
import gp.framework.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 码农界的小学生
 * @description:短信验证
 * @title: SmsService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/26 22:55
 */
@Service
public class SmsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${gp.mq.sms_routing_reset}")
    public String sms_routing_reset;
    @Value("${gp.mq.sms_routing_password}")
    public String sms_routing_password;
    /**
     * 向mq发送消息 让其发送短信
     * @param phone
     * @return
     */
    public ResponseResult sendMessage(String phone,String code) {
        if(StringUtils.isEmpty(phone)){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //验证电话号码是否存在
        User user = userRepository.findByPhone(phone);
        if(user == null){
            ExceptionCast.cast(UserCode.NUMBER_NOT_FOUND);
        }
        //向cookie存放验证码
        this.saveCookie(phone,code);
        //向mq发送消息
        HashMap<String, String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("code",code);
        String jsonString = JSON.toJSONString(map);
        rabbitTemplate.convertAndSend(RabbitmqConfig.GP_ROUTING_CMS_EXCHANGE,sms_routing_reset,jsonString);
        return new ResponseResult(UserCode.MESSAGE_SEND_SUCCESS);
    }
    /**
     * 将验证码存储到cookie
     * @param phone
     * @param code
     */
    private void saveCookie(String phone,String code){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //addCookie(HttpServletResponse response,String domain,String path, String name,
        //                                 String value, int maxAge,boolean httpOnly)
        //将code进行base64编码
        byte[] bytes = Base64Utils.encode(code.getBytes());
        //时间为两分钟
        CookieUtil.addCookie(response,"graduation.com","/",phone,new String(bytes),2*60,false);
    }

    /**
     * 发送密码到手机
     * @param phone
     * @param password
     * @return
     */
    public ResponseResult sendPassword(String phone, String password) {
        Map map = new HashMap<String,String>();
        map.put("phone",phone);
        map.put("password",password);
        String jsonString = JSON.toJSONString(map);
        rabbitTemplate.convertAndSend(RabbitmqConfig.GP_ROUTING_CMS_EXCHANGE,sms_routing_password,jsonString);
        return new ResponseResult(UserCode.PASSWORD_SEND_SUCCESS);
    }

    /**
     * 查询号码是否被使用，否则发送验证码
     * @param phone
     * @return
     */
    public ResponseResult verifyPhone(String phone,String code) {
        if(StringUtils.isEmpty(phone)){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
//        //验证是否是数字
//        boolean numeric = this.isNumeric(phone);
//        if(!numeric){
//            ExceptionCast.cast(UserCode.PHONE_IS_NUMBER);
//        }
        User user = userRepository.findByPhone(phone);
        if(user != null){
            return new ResponseResult(UserCode.NUMBER_FOUND);
        }
        //向cookie存放验证码
        this.saveCookie(phone,code);
        //向mq发送消息
        HashMap<String, String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("code",code);
        String jsonString = JSON.toJSONString(map);
        rabbitTemplate.convertAndSend(RabbitmqConfig.GP_ROUTING_CMS_EXCHANGE,sms_routing_reset,jsonString);
        return new ResponseResult(UserCode.MESSAGE_SEND_SUCCESS);
    }
    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
