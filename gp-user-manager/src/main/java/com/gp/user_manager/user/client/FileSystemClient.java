package com.gp.user_manager.user.client;

import com.gp.framework.client.gpServiceList;
import com.gp.framework.model.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 码农界的小学生
 * @description:
 * @title: FileSystemClient
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 21:51
 */
@FeignClient(value = gpServiceList.GP_FILE_MANAGER)
public interface FileSystemClient {

    @DeleteMapping("/filesystem/deletefile")
    public ResponseResult deleteFile(@RequestParam("fileId") String fileId);
}
