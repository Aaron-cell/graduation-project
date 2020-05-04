package com.gp.cms_manager.pay.controller;

import com.alipay.api.AlipayApiException;
import com.gp.api.cms.pay.PayControllerApi;
import com.gp.cms_manager.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author 码农界的小学生
 * @description:
 * @title: PayController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/23 21:39
 */
@RequestMapping("/cms/alipay")
@RestController
public class PayController implements PayControllerApi {
    @Autowired
    private PayService payService;

    @Override
    @RequestMapping(value = "/pay")
    public String aliPay(@RequestParam("id")String id,
                         @RequestParam("type")String type,@RequestParam("money")String money) throws IOException {
        return payService.aliPay(id,type,money);
    }

    @Override
    @RequestMapping ("/notify")
    public void payNotice(HttpServletRequest request,HttpServletResponse response) throws Exception {
         payService.alipayNotify(request,response);
    }

}
