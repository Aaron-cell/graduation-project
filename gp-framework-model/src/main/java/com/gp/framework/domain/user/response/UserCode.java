package com.gp.framework.domain.user.response;

import com.gp.framework.domain.user.User;
import com.gp.framework.model.response.ResultCode;
import lombok.Data;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:
 * @title: UserCode
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/16 22:50
 */
public enum UserCode implements ResultCode {
    USERNAME_EXISTS(false,20000,"用户名已存在！"),
    PASSWORD_INCONSISTENCY(false,20001,"两次密码输入不一致！"),
    REGISTER_SUCCESS(true,19999,"注册成功"),
    TIMEFORMAT_ERROR(false,20003,"时间格式错误！"),
    NUMBER_NOT_FOUND(false,20004,"该号码未被绑定"),
    NUMBER_FOUND(false,20009,"该号码已被绑定"),
    PHONE_IS_NUMBER(false,20010,"号码必须为数字"),
    ORIGINAL_PASSWORD_ERROR(false,20011,"原始密码不正确！"),
    MESSAGE_SEND_SUCCESS(true,20005,"验证码已发送"),
    CODE_FAILURE(false,20006,"验证码失效！请重新验证"),
    CODE_ERROR(false,20007,"验证码错误"),
    PASSWORD_SEND_SUCCESS(true,20008,"重置密码已发送到手机");
    //是否操作成功
    boolean success;
    //操作代码
    int code;
    //操作信息
    String message;

    UserCode(boolean success,int code,String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
