package com.gp.mq_manager.sms.service;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 码农界的小学生
 * @description:实现短信发送业务层
 * @title: SmsSendCodeService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/27 0:01
 */
@Service
public class SmsSendCodeService {
    // 短信应用 SDK AppID
    int appid = 1400339882; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    String appkey = "d877e84ed2b6579d1f83463f97662d22";
    // 签名
    String smsSign = "码农界小学生"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
    /**
     * 发送重置验证码
     * @param phone
     */
    public void SmsSend(String phone,String code) {
        // 短信模板 ID，需要在短信应用中申请
        int templateId = 563864; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
        ArrayList<String> list = new ArrayList<>();
        list.add(code);
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
                    templateId,list, smsSign, "", "");
            System.out.println("验证码码为："+code);
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

    /**
     * 重置密码后发送密码到手机
     * @param phone
     * @param password
     */
    public void SmsSendPassword(String phone, String password) {
        // 短信模板 ID，需要在短信应用中申请
        int templateId = 564693; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
        ArrayList<String> list = new ArrayList<>();
        list.add(password);
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
                    templateId,list, smsSign, "", "");
            System.out.println("重置密码为："+password);
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
