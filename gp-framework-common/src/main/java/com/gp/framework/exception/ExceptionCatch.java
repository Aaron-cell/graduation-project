package com.gp.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.ResponseResult;
import com.gp.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 码农界的小学生
 * @description:自定义异常捕获
 * @title: ExceptionCatch
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 1:45
 */
@ControllerAdvice
/**controller增强器
 *作用：给所用controller添加统一操作或处理
 * 这里结合@ExceptionHandler使用用于捕获Controller抛出的异常，实现不同类型异常区别处理
 **/
public class ExceptionCatch {
    //获取日志实例
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);
    //定义一个map 用于存放异常类 以及 对应的响应代码 Throwable 所有异常类的父类
    //ImmutableMap的特点的一旦创建不可改变，并且线程安全
    protected static ImmutableMap<Class<?extends Throwable>, CommonCode> EXCEPTIONS;
    //builder实例用于构建ImmutableMap
    protected static ImmutableMap.Builder<Class<?extends Throwable>, CommonCode> builder = ImmutableMap.builder();

    //捕获CustomException异常类
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult CatchCustomException(CustomException customException){
        LOGGER.error("catch CustomException : "+ customException.getResultCode());
        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult(resultCode);
    }

    //捕获Exception异常类
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult CatchException(Exception exception){
        LOGGER.error("catch Exception : "+ exception.getMessage());
        //如果EXCEPTIONS为空 则构建
        if(EXCEPTIONS == null){
            EXCEPTIONS = builder.build();
        }
        //从EXCEPTIONS中查看是否有对应的异常，如果有则返回该错误信息，反之返回99999
        CommonCode commonCode = EXCEPTIONS.get(exception.getClass());
        if(commonCode != null){
            return new ResponseResult(commonCode);
        }
        return new ResponseResult(CommonCode.SERVER_ERROR);
    }

    static {
        //定义 json反序列化出错响应代码 无解
        builder.put(HttpMessageNotReadableException.class,CommonCode.INVALID_PARAM);
    }
}
