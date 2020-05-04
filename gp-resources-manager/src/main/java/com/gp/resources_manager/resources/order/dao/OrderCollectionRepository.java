package com.gp.resources_manager.resources.order.dao;

import com.gp.framework.domain.resource.order.OrderCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 码农界的小学生
 * @description:
 * @title: OrderCollectionRepository
 * @projectName xc-edu
 * @description: TODO
 * @date 2019/12/4 22:43
 */
public interface OrderCollectionRepository extends MongoRepository<OrderCollection,String> {
}
