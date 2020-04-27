package com.gp.framework.model.response;

import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:
 * @title: CommonCode
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 1:11
 */
@ToString
public enum CommonCode implements ResultCode {
    INVALID_PARAM(false,10003,"非法参数"),
    SUCCESS(true,10000,"操作成功！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！"),

    FAIL(false,9999,"操作失败！");
    //是否操作成功
    boolean success;
    //操作代码
    int code;
    //操作信息
    String message;

    CommonCode(boolean success, int code, String message) {
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
