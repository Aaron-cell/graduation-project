package com.gp.cms_manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @author 码农界的小学生
 * @description:阿里支付配置信息
 * @title: AliDevPayConfig
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/23 19:06
 */
@Configuration
public class AliDevPayConfig implements Serializable {
    //页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
// 这个是支付完成后的请求
    public static String returnUrl = "http://3111h4q179.zicp.vip/alipay/return";
    //服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
// 这个是支付成功失败以后的通知接口会返回相应的参数
    public static String notifyUrl = "http://3111h4q179.zicp.vip/alipay/notify";

    /**支付宝分配给开发者的应用ID*/
    public static String aliPayAppId = "2016102400752951";
    /**
     * 商户id
     */
    public static String uid = "2088102180918899";
    /**支付宝网关*/
    public static String aliPayGateWay = "https://openapi.alipaydev.com/gateway.do";
    public static  String mcloud_api_domain = "http://mcloudmonitor.com/gateway.do";
    /**私钥*/
    public static String aliPayPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCtPPBOHRZyOPH2Qn4IAWmY+qrJaYSs5PZwRtAxm7oPcgChnacGr8uTZKtZtNFMwoI1RXqutWAWjMhVNHv9JcAdZbL7LsXO8AuP09YcFpGGXEWFoze3RGeN4Oiy1JhC21nkZONXn2RlmR44dHNLvH3p213MjR6omV16qG0SqFyCQ6m3DPVvXn+aclw+0vZPa6wvjKFn57WdgFrrwHSKMvuPoz5qW4Yv7wu4isXyOowczyfX0DERVvcdYdaIPUOEBEN6EcKbVq8/FC1zMq79REBU/iVtVjWunRe6FMq+jbzNsVvZdwm2FEG2hcnw+GmvZUMIstUf3dm3yZiMqULPCGf/AgMBAAECggEAdingx4czrkmFUTHdgVxlSMGCCMv+gWfTaJYEoDHWYX6jS5mbOgZi13CNKC0brj3mTqvLmKuClR8F7ohUIkHDnsmloYsvxY0adcpKevIx5PG/Qf4onMr9z1qfnotAKCoyUIU4RKYl9t7QLDXSBtDVGdRVUJbZWKrzhhyn+Vf5cafOyyQEgMAonAYqkLweZilyFO5TGlgLKJsKXhQjltZrPxVebQ9PowIz47NvQVOBya+WGTAkyN1Bri10ZLxr62VGhUjUGKcRL0qVxtaXpafARcW5HU1U/aJlkX9stHZwRlLsxdN5MV1DwlKInX9shYHOHheCsohlB7ZaZYmwrVulEQKBgQD1cmknrwkiYgocPq7NhQ89IddmWgduqkTE9FbaDXtM7YrvTIPGJDCE5d71R+sw8GG36M0OQxitdB72mbv6md+FxqmAtlD6d0L5V0mJfRErW4W48NhCI03XIWUcy3JrtqVF1N7qlB7D5IkW3DBqFVHf2iO97oMczhhsQsVnrH0VmwKBgQC0r7zyS6DwV1Pb8q3n1dmWD/wg4PuV35PC/uZ5CXiYWOcbaZZM1+3QQ56F/qZugwsb7aXbl7lL67hZ70GcQJPC8YBHebrBK9q/8C29TkPigzY0jWgM4rFbcb/raRKP3Oy1T4itIUH1/nuWwZT2AoT8lLRbLmeUeDCvg3GsbrhvbQKBgQCxhkvKOQ5pQPHBFhFGcsvf0l9CFzGy+BH/Rh6fXgrlTBYmGHhi0oAJT12gmKDiZ6q14vqVKzBpHEjc4bqVeb+yuUqV3sfZMHNPdrOobr4BVxPz/Lbdtz0wsWW5muiqQZNuW5XToshRtTT2RNH7mGn8d4FMeXt5VRNTEkRHRPm/qwKBgEXtGS+gCznYYhDmG110io+jwgyrZVI8Q4Aci+9dtfknttEKDOvSSSvnb2smAR0Vw+/cCesxDboPELpleLvS1hyEwANpXdgyc6cCeYEgz+7SnuC5tQH/nWnpXL/rWw6oDkeg7yEplNBx2zyd0Ftg4DysBByhmd6AoT7bnNuBwgkFAoGAVYXQYh+YgM5mKaagBZOWqwhkP4Pr8Oo44G5RaswP/O7R4ulgQ48BsaPifngek+tDTetX3nlZjogb1YtIEEJNJ1SSPsvEZGzR0cwgysp+wRjER5O+M2rvNWc3G+a4wM864eYuCq1n73xL9FqXYIJXjRlmLobejYiOqYa02bRIpQc=";
    /**支付宝公钥*/
    public static String aliPayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAix4U83rjS6rT8w8njXFEU+SAYH/zCCRQc98PpUDEBBx3qY7CaJPL0QPETs1WyufqZYRSKbW0Ml9wTA/SXgOp3bJn5v78vhe2LSFtmr0gCyNJXtSDrz91XgR/oK3T5j0yy8NgOMBjdjnI2a6cpqrt3YXkc02hhJTfG+3o6f+gGw6wtU7CC0BTGoH//lk+prNerjeFQLZd7hB0ANlC5JdSfDBHtr/+gPcL7ap84nEJA1TSOCnQP8h2UYd5MAwqwyqitK3B9hEUCWI7Mp3VgI+CAGVXXnUzL74hUK26yrTczucX9bEPUUAyrlxWIUZ3OsX07KwpL1PLnKH/HYJStFG8TQIDAQAB";
    public static String FORMAT = "JSON";

    /**请求使用的编码格式，如utf-8,gbk,gb2312等*/
    public static String CHARSET = "utf-8";

    /**商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2*/
    public static String SIGN_TYPE = "RSA2";
}
