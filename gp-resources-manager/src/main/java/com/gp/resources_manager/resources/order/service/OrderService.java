package com.gp.resources_manager.resources.order.service;

import com.gp.framework.domain.resource.order.OrderCollection;
import com.gp.framework.domain.resource.order.QueryOrderRequest;
import com.gp.framework.domain.resource.order.Order;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.QueryResult;
import com.gp.framework.model.response.ResponseResult;
import com.gp.resources_manager.resources.order.dao.OrderCollectionRepository;
import com.gp.resources_manager.resources.order.dao.OrderRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author 码农界的小学生
 * @description:
 * @title: OrderService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/29 0:18
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderCollectionRepository orderCollectionRepository;
    /**
     * 添加订单
     * @param order
     * @return
     */
    @Transactional
    public ResponseResult InsertOrder(Order order) {
        if(order == null){
            return new ResponseResult(CommonCode.INVALID_PARAM);
        }
        orderRepository.save(order);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 查询订单列表
     * @param page
     * @param size
     * @param queryOrderRequest
     * @return
     */
    @Transactional
    public QueryResponseResult<Order> getOrderList(int page, int size, QueryOrderRequest queryOrderRequest) {
        Order order = new Order();
        if(queryOrderRequest !=null && StringUtils.isNotEmpty(queryOrderRequest.getHandler())){
            order.setHandler(queryOrderRequest.getHandler());
        }
        if(queryOrderRequest !=null && StringUtils.isNotEmpty(queryOrderRequest.getName())){
            order.setName(queryOrderRequest.getName());
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("handler", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Order> example = Example.of(order, exampleMatcher);
        PageRequest request = new PageRequest(page, size);
        Page<Order> all = orderRepository.findAll(example, request);
        QueryResult<Order> queryResult = new QueryResult<>();
        queryResult.setTotal(all.getTotalElements());
        queryResult.setList(all.getContent());
        return new QueryResponseResult<Order>(CommonCode.SUCCESS,queryResult);
    }

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    public OrderCollection getOrderDetails(String id) {
        if(StringUtils.isEmpty(id)){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Optional<OrderCollection> byId = orderCollectionRepository.findById(id);
        if(!byId.isPresent()){
            ExceptionCast.cast(CommonCode.SERVER_ERROR);
        }
        return byId.get();
    }
}
