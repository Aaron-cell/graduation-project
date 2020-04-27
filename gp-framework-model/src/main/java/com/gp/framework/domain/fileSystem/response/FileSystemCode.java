package com.gp.framework.domain.fileSystem.response;

import com.gp.framework.model.response.ResultCode;
import lombok.Data;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:
 * @title: FileSystemCode
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 17:02
 */
public enum  FileSystemCode implements ResultCode {
    FILE_UPLOAD_SUCCESS(true,25000,"文件上传成功"),
    FS_UPLOADFILE_FILEISNULL(false,25001,"上传文件为空！"),
    FS_UPLOADFILE_SERVERFAIL(false,25003,"上传文件服务器失败！"),
    FS_DELETEFILE_NOTEXISTS(false,25004,"删除的文件不存在！"),
    FS_DELETEFILE_DBFAIL(false,25005,"删除文件信息失败！"),
    FS_DELETEFILE_SERVERFAIL(false,25006,"删除文件失败！"),
    FS_INFO_EMPTY(false,25007,"文件id为空"),
    FS_USERID_EMPTY(false,25008,"用户id为空"),
    FS_INITFDFSERROR(false,25009,"初始化FastDFS环境出错");
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //响应信息
    String message;
    FileSystemCode(boolean success, int code, String message){
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
