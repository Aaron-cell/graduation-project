package com.gp.mq_manager.email.service;

import com.gp.framework.domain.cms.email.BirthTemplet;
import com.gp.framework.domain.cms.email.SysDictionary;
import com.gp.mq_manager.email.dao.SysDictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;

/**
 * @author 码农界的小学生
 * @description:邮件发送服务
 * @title: EmailSendService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/28 0:29
 */
@Service
public class EmailSendService {
    @Autowired
    private SysDictionaryRepository sysDictionaryRepository;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${birthTemplet.id}")
    private String id;//生日模板id
    /**
     * 获取生日模板内容 向邮箱发送邮件
     * @param email
     */
    public void emailSend(String email) {
        //获取模板jihe
        Optional<SysDictionary<BirthTemplet>> byId = sysDictionaryRepository.findById(id);
        SysDictionary<BirthTemplet> templetSysDictionary = byId.get();
        List<BirthTemplet> list = templetSysDictionary.getValue();
        //获取模板样例
        for(BirthTemplet birthTemplet : list){
            //Status = 1 当前使用生日模板
            if(birthTemplet.getStatus().equals("1")){
                //发送邮件
                this.emailSendToUser(email,birthTemplet.getContent());
                break;//跳出循环
            }
        }
    }

    /**
     * 发送邮件
     * @param email
     * @param content
     */
    private void emailSendToUser(String email,String content){
        //1.创建一个复杂的消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("HappyBirthday");
            helper.setText(content,true);
            helper.setTo(email);
            helper.setFrom("1464889572@qq.com");
            //文件上传
//        helper.addAttachment("1.jpg",new File("F:\\word\\1438185311714017.jpg"));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
