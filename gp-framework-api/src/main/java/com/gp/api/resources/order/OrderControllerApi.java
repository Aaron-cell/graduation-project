package com.gp.api.resources.order;

import com.gp.framework.domain.resource.order.OrderCollection;
import com.gp.framework.domain.resource.order.QueryOrderRequest;
import com.gp.framework.domain.resource.order.Order;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 码农界的小学生
 * @description:
 * @title: OrderControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/29 0:12
 */
@Api("订单管理")
public interface OrderControllerApi {
    @ApiOperation("添加订单")
    public ResponseResult InsertOrder(Order order);

    @ApiOperation("查询订单列表")
    public QueryResponseResult<Order> getOrderList(int page, int size, QueryOrderRequest queryOrderRequest);

    @ApiOperation("获取订单详情")
    public OrderCollection getOrderDetails(String _id);
}
