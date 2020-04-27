package com.gp.mq_manager.sms.mq;

import com.alibaba.fastjson.JSON;
import com.gp.mq_manager.sms.service.SmsSendCodeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 码农界的小学生
 * @description:监听队列
 * @title: SmsSendCode
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/27 0:00
 */
@Component
public class SmsSendCode {
    @Autowired
    private SmsSendCodeService smsSendCodeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSendCode.class);

    /**
     * 监听${gp.mq.sms_queue_reset} 队列
     * @param msg
     */
    @RabbitListener(queues = {"${gp.mq.sms_queue_reset}"})
    public void SmsSendCode(String msg){
        Map map = JSON.parseObject(msg, Map.class);
        String phone = (String) map.get("phone");
        String code = (String) map.get("code");
        if(StringUtils.isEmpty(phone)){
            LOGGER.error("receive smsSendCode,phone is null,phone:{}",phone);
        }
        if(StringUtils.isEmpty(code)){
            LOGGER.error("receive smsSendCode,phone is null,code:{}",code);
        }
        smsSendCodeService.SmsSend(phone,code);
    }

    /**
     * 监听发送重置密码的队列
     * @param msg
     */
    @RabbitListener(queues = {"${gp.mq.sms_queue_password}"})
    public void SmsSendPassword(String msg){
        Map map = JSON.parseObject(msg, Map.class);
        String phone = (String) map.get("phone");
        String password = (String) map.get("password");
        if(StringUtils.isEmpty(phone)){
            LOGGER.error("receive SmsSendPassword,phone is null,phone:{}",phone);
        }
        if(StringUtils.isEmpty(password)){
            LOGGER.error("receive SmsSendPassword,password is null,code:{}",password);
        }
        smsSendCodeService.SmsSendPassword(phone,password);
    }
}
