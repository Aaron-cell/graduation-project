package com.gp.mq_manager.email.mq;

import com.alibaba.fastjson.JSON;
import com.gp.mq_manager.email.service.EmailSendService;
import com.gp.mq_manager.sms.mq.SmsSendCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 码农界的小学生
 * @description:
 * @title: EmailSend
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/28 0:20
 */
@Component
public class EmailSend {
    @Autowired
    private EmailSendService emailSendService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSend.class);
    /**
     * 监听发送生日邮件队列
     * @param msg
     */
    @RabbitListener(queues = {"${gp.mq.email_queue_birth}"},containerFactory = "customContainerFactory")
    public void emailSend(String msg){
        Map map = JSON.parseObject(msg, Map.class);
        String email = (String) map.get("email");
        if(StringUtils.isEmpty(email)){
            LOGGER.error("receive EmailSend,email is null,email:{}",email);
        }
        emailSendService.emailSend(email);
    }
}
