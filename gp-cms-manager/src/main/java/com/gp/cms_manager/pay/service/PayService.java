package com.gp.cms_manager.pay.service;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.gp.cms_manager.config.AliDevPayConfig;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 码农界的小学生
 * @description:调用支付宝接口业务逻辑
 * @title: PayService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/23 21:43
 */
@Service
public class PayService {
    /**
     * 调用支付宝接口
     * @return
     */
    public String aliPay() {
        //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型），为了取得预付订单信息
        AlipayClient alipayClient = new DefaultAlipayClient(AliDevPayConfig.aliPayGateWay,AliDevPayConfig.aliPayAppId,
                AliDevPayConfig.aliPayPrivateKey,AliDevPayConfig.FORMAT,AliDevPayConfig.CHARSET,AliDevPayConfig.aliPayPublicKey,
                AliDevPayConfig.SIGN_TYPE);
        //实例化具体API对应的request类
        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        payRequest.setNotifyUrl(AliDevPayConfig.notifyUrl);
        payRequest.setReturnUrl(AliDevPayConfig.returnUrl);
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式
        AlipayTradePagePayModel payModel = new AlipayTradePagePayModel();
        //商户订单号
        payModel.setOutTradeNo("300000");
        //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
        payModel.setBody("xx商品介绍");
        //商品名称
        payModel.setSubject("xx商品");
        //商户订单号(根据业务需求自己生成)
        payModel.setOutTradeNo("你自己业务账单的id");
        //交易超时时间 这里的30m就是30分钟
        payModel.setTimeoutExpress("30m");
        //支付金额 后面保留2位小数点..不能超过2位
        payModel.setTotalAmount("100.00");
        // //电脑网站支付销售产品码，不同的支付方式productCode不同 销售产品码（固定值）
        payModel.setProductCode("FAST_INSTANT_TRADE_PAY");
        payRequest.setBizModel(payModel);
        try {
            // 这里和普通的接口调用不同，使用的是sdkExecute
            //返回支付宝订单信息(预处理)
            AlipayTradePagePayResponse alipayTradePagePayResponse = alipayClient.pageExecute(payRequest);
            //就是orderString 可以直接给APP请求，无需再做处理。
            String result = alipayTradePagePayResponse.getBody();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 接口回调
     * @param request
     * @return
     */
    public String alipayNotify(HttpServletRequest request) {
        System.out.println("调用成功");
        return null;
    }
}
