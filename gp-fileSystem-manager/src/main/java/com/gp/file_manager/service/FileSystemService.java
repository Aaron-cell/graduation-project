package com.gp.file_manager.service;

import com.gp.file_manager.dao.FileSystemRepository;
import com.gp.framework.domain.fileSystem.FileSystem;
import com.gp.framework.domain.fileSystem.response.FileSystemCode;
import com.gp.framework.domain.fileSystem.response.UploadResult;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 码农界的小学生
 * @description:
 * @title: FileSystemService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 17:39
 */
@Service
public class FileSystemService {
    //注入fastdfs配置信息
    @Value("${gp.fastdfs.tracker_servers}")
    String tracker_servers;
    @Value("${gp.fastdfs.connect_timeout_in_seconds}")
    int connect_timeout_in_seconds;
    @Value("${gp.fastdfs.network_timeout_in_seconds}")
    int network_timeout_in_seconds;
    @Value("${gp.fastdfs.charset}")
    String charset;
    @Autowired
    private FileSystemRepository fileSystemRepository;

    /**
     * 初始化文件系统环境
     */
    private void initFastDFS(){
        try {
            ClientGlobal.setG_charset(charset);
            ClientGlobal.setG_connect_timeout(connect_timeout_in_seconds);
            ClientGlobal.setG_network_timeout(network_timeout_in_seconds);
            ClientGlobal.initByTrackers(tracker_servers);
        } catch (Exception e) {
            e.printStackTrace();
            //初始化文件系统出错
            ExceptionCast.cast(FileSystemCode.FS_INITFDFSERROR);
        }
    }
    /**
     * 文件上传
     * @param multipartFile 文件
     * @return
     */
    public UploadResult uploadFile(MultipartFile multipartFile) {
        if(multipartFile == null){
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_FILEISNULL);
        }
        //开始文件上传
        String fileId = this.uploadFastDFS(multipartFile);
        //filePath为空 表示上传失败
        if(StringUtils.isEmpty(fileId)){
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_SERVERFAIL);
        }
        //组装对象
        FileSystem fileSystem = new FileSystem();
        fileSystem.setFileId(fileId);
        String filename = multipartFile.getOriginalFilename();
        fileSystem.setFileName(filename);
        fileSystem.setFileSize(multipartFile.getSize());
        fileSystem.setFilePath(fileId);
        fileSystem.setFileType(multipartFile.getContentType());
        fileSystemRepository.save(fileSystem);
        return new UploadResult(FileSystemCode.FILE_UPLOAD_SUCCESS,fileSystem);
    }
    //上传文件
    private String uploadFastDFS(MultipartFile multipartFile){
        this.initFastDFS();
        //开始上传文件
        TrackerClient trackerClient = new TrackerClient();
        try {
            //获取一个tracker服务端
            TrackerServer connection = trackerClient.getConnection();
            //根据tracker服务端获取storage服务端
            StorageServer storeStorage = trackerClient.getStoreStorage(connection);
            //获取Storage客户端
            StorageClient1 storageClient1 = new StorageClient1(connection, storeStorage);
            //获取文件扩展名
            String filename = multipartFile.getOriginalFilename();
            String ext = filename.substring(filename.lastIndexOf(".")+1);
            //上传文件
            String fileId = storageClient1.upload_file1(multipartFile.getBytes(), ext, null);
            return fileId;
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_SERVERFAIL);
        }
        return null;
    }
    //删除文件
    public ResponseResult deleteFile(String fileId) {
        if(StringUtils.isEmpty(fileId)){
            ExceptionCast.cast(FileSystemCode.FS_INFO_EMPTY);
        }
        this.initFastDFS();
        //删除文件
        TrackerClient trackerClient = new TrackerClient();
        try {
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
            storageClient1.delete_file1(fileId);
            //删除MongoDB
            fileSystemRepository.deleteById(fileId);
            return new ResponseResult(CommonCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionCast.cast(FileSystemCode.FS_DELETEFILE_SERVERFAIL);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}
