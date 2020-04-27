package com.gp.api.cms.pay;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 码农界的小学生
 * @description:
 * @title: PayControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/23 21:31
 */
@Api("[支付宝充值接口]")
public interface PayControllerApi {

    @ApiOperation("调用支付宝充值接口")
    public void aliPay(HttpServletRequest request, HttpServletResponse response) throws IOException;

    @ApiOperation("[支付宝专用]支付宝充值接口异步回调接口")
    public String payNotice(HttpServletRequest request);

    @ApiOperation("[支付宝专用]支付宝充值接口同步回调接口")
    public String payReturn(HttpServletRequest request);
}
