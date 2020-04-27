package com.gp.cms_manager.sms.controller;

import com.gp.api.cms.sms.SmsControllerApi;
import com.gp.cms_manager.sms.service.SmsService;
import com.gp.framework.domain.user.response.UserCode;
import com.gp.framework.model.response.ResponseResult;
import gp.framework.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 码农界的小学生
 * @description:
 * @title: SmsController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/26 22:52
 */
@RestController
@RequestMapping("/cms/sms")
public class SmsController implements SmsControllerApi {
    @Autowired
    private SmsService smsService;

    /**
     * 重置密码发送验证码
     * @param phone
     * @return
     */
    @Override
    @GetMapping("/send/{phone}")
    public ResponseResult SendMessage(@PathVariable("phone") String phone) {
        //随机生成6位数
        int newNum = (int)((Math.random()*9+1)*100000);
        return smsService.sendMessage(phone, String.valueOf(newNum));
    }

    @Override
    @GetMapping("/send/password/{phone}/{password}")
    public ResponseResult SendPassword(@PathVariable("phone") String phone,
                                       @PathVariable("password") String password) {
        return smsService.sendPassword(phone,password);
    }

}
