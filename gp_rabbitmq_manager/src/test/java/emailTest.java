import com.gp.mq_manager.RabbitMQApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;

/**
 * @author 码农界的小学生
 * @description:
 * @title: emailTest
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/27 22:48
 */
@SpringBootTest(classes = RabbitMQApplication.class)
@RunWith(SpringRunner.class)
public class emailTest {
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Test
    public void test01()throws Exception{
        //1.创建一个复杂的消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("今晚开会");
        helper.setText("<h2><span style=\\\"color: rgb(230, 0, 0);\\\">生日快乐</span></h2><p><br></p>",true);
        helper.setTo("1464889572@qq.com");
        helper.setFrom("1464889572@qq.com");
        //文件上传
//        helper.addAttachment("1.jpg",new File("F:\\word\\1438185311714017.jpg"));
        javaMailSender.send(mimeMessage);
    }
}
