package com.gp.cms_manager;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author 码农界的小学生
 * @description:验证短信发送功能
 * @title: MessageTest
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/26 19:46
 */
@SpringBootTest(classes = CmsApplication.class)
@RunWith(SpringRunner.class)
public class MessageTest {

    @Test
    public void SendMessage(){
        // 短信应用 SDK AppID
        int appid = 1400339882; // SDK AppID 以1400开头
        // 短信应用 SDK AppKey
        String appkey = "d877e84ed2b6579d1f83463f97662d22";
        // 需要发送短信的手机号码
        String[] phoneNumbers = {"13163346913"};
        // 签名
        String smsSign = "码农界小学生"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
        // 短信模板 ID，需要在短信应用中申请
        int templateId = 563864; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
        try {
            String[] params = {"5678"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
    }
}
