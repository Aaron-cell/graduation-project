package com.gp.api.cms.pay;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
    public String aliPay(String id,String type,String money) throws IOException;

    @ApiOperation("[支付宝专用]支付宝充值接口异步回调接口")
    public void payNotice(HttpServletRequest request,HttpServletResponse response) throws Exception;

}
