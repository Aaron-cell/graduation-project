package com.gp.user_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 码农界的小学生
 * @description:springBoot启动类
 * @title: manageResourcesApplication
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 2:39
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
//扫描一下包中注解 本项目下默认自动扫描
@EntityScan("com.gp.framework.domain.user")//扫描实体类
@ComponentScan(basePackages = {"com.gp.api","com.gp.framework","com.gp.user_manager"})
public class manageUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(manageUserApplication.class,args);
    }
}
