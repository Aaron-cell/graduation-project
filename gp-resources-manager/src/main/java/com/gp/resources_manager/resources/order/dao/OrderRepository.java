package com.gp.resources_manager.resources.order.dao;

import com.gp.framework.domain.resource.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 码农界的小学生
 * @description:用户持久层
 * @title: UserRepository
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/16 22:39
 */
public interface OrderRepository extends JpaRepository<Order,String> {

}
