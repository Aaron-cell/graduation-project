package com.gp.user_manager.user.client;

import com.gp.framework.client.gpServiceList;
import com.gp.framework.model.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 码农界的小学生
 * @description:
 * @title: FileSystemClient
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 21:51
 */
@FeignClient(value = gpServiceList.GP_CMS_MANAGER)
public interface CmsClient {
    //发送密码到手机
    @GetMapping("/cms/sms/send/password/{phone}/{password}")
    public ResponseResult SendPassword(@PathVariable("phone") String phone,
                                       @PathVariable("password") String password);
}
