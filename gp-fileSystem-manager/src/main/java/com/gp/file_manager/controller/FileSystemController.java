package com.gp.file_manager.controller;

import com.gp.api.file.FileSystemControllerApi;
import com.gp.file_manager.service.FileSystemService;
import com.gp.framework.domain.fileSystem.response.UploadResult;
import com.gp.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 码农界的小学生
 * @description:
 * @title: FileSystemController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 17:37
 */
@RestController
@RequestMapping("/filesystem")
public class FileSystemController implements FileSystemControllerApi {
    @Autowired
    private FileSystemService fileSystemService;
    @Override
    @PostMapping("/upload")
    public UploadResult uploadFile(MultipartFile multipartFile) {
        return fileSystemService.uploadFile(multipartFile);
    }

    @Override
    @DeleteMapping("/deletefile")
    public ResponseResult deleteFile(@RequestParam("fileId") String fileId) {
        return fileSystemService.deleteFile(fileId);
    }
}
