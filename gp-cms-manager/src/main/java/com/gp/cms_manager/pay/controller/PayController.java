package com.gp.cms_manager.pay.controller;

import com.gp.api.cms.pay.PayControllerApi;
import com.gp.cms_manager.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 码农界的小学生
 * @description:
 * @title: PayController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/23 21:39
 */
@RequestMapping("/alipay")
@RestController
public class PayController implements PayControllerApi {
    @Autowired
    private PayService payService;

    @Override
    @RequestMapping(value = "/pay")
    public void aliPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(payService.aliPay());
        response.getWriter().flush();
        response.getWriter().close();
    }

    @Override
    @RequestMapping ("/notify")
    public String payNotice(HttpServletRequest request) {
        return payService.alipayNotify(request);
    }

    @Override
    @RequestMapping("/return")
    public String payReturn(HttpServletRequest request) {
        System.out.println("PayController.payReturn");
        return null;
    }
}
