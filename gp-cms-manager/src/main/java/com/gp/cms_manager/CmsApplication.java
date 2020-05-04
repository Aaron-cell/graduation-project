package com.gp.cms_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 码农界的小学生
 * @description:
 * @title: CmsApplication
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/26 19:34
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling //开启定时任务
@EnableAsync //开启异步任务
@EnableFeignClients
//扫描一下包中注解 本项目下默认自动扫描
@EntityScan("com.gp.framework.domain")//扫描实体类
@ComponentScan(basePackages = {"com.gp.api","com.gp.framework","com.gp.cms_manager"})
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
