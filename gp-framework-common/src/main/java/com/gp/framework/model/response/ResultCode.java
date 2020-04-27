package com.gp.framework.model.response;

/**
 * @author 码农界的小学生
 * @description:结果响应码
 * @title: ResultCode
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 1:06
 */
public interface ResultCode {
    //是否操作成功
    public boolean success();
    //获取结果码
    public int code();
    //获取结果信息描述
    public String message();
}
