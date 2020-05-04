package com.gp.api.cms.sms;

import com.gp.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 码农界的小学生
 * @description:
 * @title: SmsControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/26 20:52
 */
@Api("短信管理")
public interface SmsControllerApi {
    @ApiOperation("重置密码根据电话号码发送验证码")
    public ResponseResult SendMessage(String phone);

    @ApiOperation("重置密码成功后发送密码到手机")
    public ResponseResult SendPassword(String phone,String password);

    @ApiOperation("根据电话号码验证数据库中是否存在，发送验证码")
    public ResponseResult VerifyPhone(String phone);

}
