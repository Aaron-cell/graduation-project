package com.gp.cms_manager.pay.client;

import com.gp.framework.client.gpServiceList;
import com.gp.framework.domain.resource.order.Order;
import com.gp.framework.model.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 码农界的小学生
 * @description:
 * @title: FileSystemClient
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 21:51
 */
@FeignClient(value = gpServiceList.GP_RESOURCES_MANAGER)
public interface OrderClient {

    @PostMapping("/resources/order/addorder")
    public ResponseResult InsertOrder(@RequestBody Order order);
}
