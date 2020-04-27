package com.gp.mq_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 码农界的小学生
 * @description:
 * @title: RabbitMQApplication
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/26 23:51
 */
@SpringBootApplication
//扫描一下包中注解 本项目下默认自动扫描
@EntityScan("com.gp.framework.domain.cms")//扫描实体类
@ComponentScan(basePackages = {"com.gp.api","com.gp.framework","com.gp.mq_manager"})
public class RabbitMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApplication.class,args);
    }
}
