package com.gp.api.file;

import com.gp.framework.domain.fileSystem.response.UploadResult;
import com.gp.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 码农界的小学生
 * @description:文件管理接口
 * @title: FileSystemControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 17:34
 */
@Api("文件管理接口")
public interface FileSystemControllerApi {
    @ApiOperation("文件上传接口")
    public UploadResult uploadFile(MultipartFile multipartFile);
    @ApiOperation("文件删除接口")
    public ResponseResult deleteFile(String fileId);
}
