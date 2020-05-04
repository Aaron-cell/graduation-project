package com.gp.resources_manager.resources.order.controller;

import com.gp.api.resources.order.OrderControllerApi;
import com.gp.framework.domain.resource.order.OrderCollection;
import com.gp.framework.domain.resource.order.QueryOrderRequest;
import com.gp.framework.domain.resource.order.Order;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import com.gp.resources_manager.resources.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author 码农界的小学生
 * @description:
 * @title: OrderController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/29 0:14
 */
@RestController
@RequestMapping("/resources/order")
public class OrderController implements OrderControllerApi {
    @Autowired
    private OrderService orderService;

    @Override
    @PostMapping("/addorder")
    public ResponseResult InsertOrder(@RequestBody Order order) {
        return orderService.InsertOrder(order);
    }

    @Override
    @PreAuthorize("hasAuthority('gp_order_getlist_all')")
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult<Order> getOrderList(@PathVariable("page") int page,@PathVariable("size") int size,
                                                   QueryOrderRequest queryOrderRequest) {
        if(page <= 0){
            page = 1;
        }
        page = page -1;
        if (size <= 0){
            size = 10;
        }
        return orderService.getOrderList(page,size,queryOrderRequest);
    }

    @Override
    @PreAuthorize("hasAuthority('gp_order_get_details')")
    @GetMapping("/details/{id}")
    public OrderCollection getOrderDetails(@PathVariable("id") String _id) {
        return orderService.getOrderDetails(_id);
    }
}
