package com.gp.framework.exception;

import com.gp.framework.model.response.ResultCode;

/**
 * @author 码农界的小学生
 * @description:定义一个静态方法 抛出自定义CustomerException异常
 * @title: ExceptionCast
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 1:41
 */
public class ExceptionCast {

    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
