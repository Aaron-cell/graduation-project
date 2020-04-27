package com.gp.framework.domain.fileSystem.response;

import com.gp.framework.domain.fileSystem.FileSystem;
import com.gp.framework.model.response.ResponseResult;
import com.gp.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 码农界的小学生
 * @description:
 * @title: UploadResult
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 17:13
 */
@Data
@NoArgsConstructor
public class UploadResult extends ResponseResult {
    FileSystem fileSystem;
    public UploadResult(ResultCode resultCode,FileSystem fileSystem){
        super(resultCode);
        this.fileSystem = fileSystem;
    }
}
