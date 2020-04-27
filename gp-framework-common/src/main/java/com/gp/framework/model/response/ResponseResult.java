package com.gp.framework.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:自定义响应结果
 * @title: ResponseResult
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 1:20
 */
@Data
@ToString
@NoArgsConstructor
public class ResponseResult implements ResponseData {
    //操作是否成功
    boolean success = SUCCESS ;

    //操作代码
    int code = SUCCESS_CODE;

    //提示信息
    String message = SUCCESS_MESSAGE;

    public ResponseResult(ResultCode resultCode){
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
    }
    public static ResponseResult SUCCESS(){
        return new ResponseResult(CommonCode.SUCCESS);
    }
    public static ResponseResult FAIL(){
        return new ResponseResult(CommonCode.FAIL);
    }
}
