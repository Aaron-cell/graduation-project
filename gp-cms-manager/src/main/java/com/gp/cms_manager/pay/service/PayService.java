package com.gp.cms_manager.pay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.gp.cms_manager.config.AliDevPayConfig;
import com.gp.cms_manager.dao.OrderCollectionRepository;
import com.gp.cms_manager.dao.SuperRepository;
import com.gp.cms_manager.dao.UserRepository;
import com.gp.cms_manager.dao.UserRoleRepository;
import com.gp.cms_manager.pay.client.OrderClient;
import com.gp.framework.domain.resource.order.Order;
import com.gp.framework.domain.resource.order.OrderCollection;
import com.gp.framework.domain.resource.order.OrderValue;
import com.gp.framework.domain.user.Super;
import com.gp.framework.domain.user.User;
import com.gp.framework.domain.user.UserRole;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.ResponseResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

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
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private OrderCollectionRepository orderCollectionRepository;
    @Autowired
    private SuperRepository superRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;
    /**
     * 调用支付宝接口
     * @return
     */
    public String aliPay(String id,String type,String money) {
        //非法参数 抛出异常
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(type) || StringUtils.isEmpty(money)){
             ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
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
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString();
        payModel.setOutTradeNo(uid);
        //对一笔交易的具体描述信息。这里存储用户id
        payModel.setBody(id);
        //商品名称
        payModel.setSubject(type);
        //交易超时时间 这里的30m就是30分钟
        payModel.setTimeoutExpress("30m");
        //支付金额 后面保留2位小数点..不能超过2位
        payModel.setTotalAmount(money);
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
     * 支付成功后接口回调 加上事务注解
     * @param request
     * @return
     */
    @Transactional
    public void alipayNotify(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, UnsupportedEncodingException {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //调用SDK签名验证
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliDevPayConfig.aliPayPublicKey, AliDevPayConfig.CHARSET,AliDevPayConfig.SIGN_TYPE);
        if(signVerified){
            //商户订单号
            String uid = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //用户id
            String userId = new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");
            //健身房会员卡类型
            String type = new String(request.getParameter("subject").getBytes("ISO-8859-1"),"UTF-8");
            //金额
            String money = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            String itemName = null;
            if(type.equals("1")){
                itemName = "健身房会员——季度卡";
            }else if (type.equals("2")){
                itemName = "健身房会员——半年卡";
            }else{
                itemName = "健身房会员——年度卡";
            }
            //实例化OrderCollection 将详细信息存入MongoDB
            OrderCollection<OrderValue> orderCollection = new OrderCollection<OrderValue>();
            orderCollection.setName(itemName);
            orderCollection.setMoney(money);
            OrderCollection<OrderValue> save = orderCollectionRepository.save(orderCollection);

            //实例化时间
            Date date = new Date();
            Calendar cd=Calendar.getInstance();//获取一个Calendar对象
            //获取用户名
            Optional<User> byId = userRepository.findById(userId);
            User user = byId.get();
            //实例化order mysql
            Order order = new Order();
            order.setType("10004");//10004会员订单
            order.setName(itemName);
            order.setHandler(user.getUsername());
            order.setDescId(save.get_id());
            order.setCreateTime(date);
            ResponseResult responseResult = orderClient.InsertOrder(order);
            if(responseResult.isSuccess()){
                //向super表中更新数据
                //1.查询super表中是否有用户充值记录
                Super result = superRepository.findByUserId(userId);

                //如果不存在 创建
                if(result == null){
                    result = new Super();
                    result.setUserId(userId);
                    result.setCreateTime(date);
                    result.setUpdateTime(date);
                    //设置结束时间
                    cd.setTime(date);
                    if (type.equals("1")){
                        cd.add(Calendar.MONTH,4);//增加4个月
                    }else if (type.equals("2")){
                        cd.add(Calendar.MONTH,6);
                    }else{
                        cd.add(Calendar.YEAR,1);
                    }
                    Date endTime = cd.getTime();
                    result.setEndTime(endTime);
                }else{
                    //存在记录 更新
                    result.setUpdateTime(date);
                    //设置结束时间
                    cd.setTime(result.getEndTime());
                    if (type.equals("健身房会员——季度卡")){
                        cd.add(Calendar.MONTH,4);//增加4个月
                    }else if (type.equals("健身房会员——半年卡")){
                        cd.add(Calendar.MONTH,6);
                    }else{
                        cd.add(Calendar.YEAR,1);
                    }
                    Date endTime = cd.getTime();
                    result.setEndTime(endTime);
                }
                //存储数据
                superRepository.save(result);

                //修改用户角色关联表 修改为会员
                UserRole userRole = userRoleRepository.findByUserId(userId);
                userRole.setRoleId(5);
                userRoleRepository.save(userRole);
            }
        }
    }
}
