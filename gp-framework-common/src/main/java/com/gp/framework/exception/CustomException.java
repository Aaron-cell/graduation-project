package com.gp.framework.exception;

import com.gp.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:自定义抛出运行异常
 * @title: CustomException
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 1:31
 */
@Data
@ToString
@NoArgsConstructor
public class CustomException extends RuntimeException {
    //继承RuntimeException运行异常，优点：对代码没有侵入性
    public ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        this.resultCode = resultCode;
    }

}
