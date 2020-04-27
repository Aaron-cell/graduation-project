package com.gp.file_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 码农界的小学生
 * @description:
 * @title: FileSystemApplication
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 17:18
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.gp.framework.domain.fileSystem")//扫描实体类
@ComponentScan(basePackages = {"com.gp.api","com.gp.framework","com.gp.file_manager"})
public class FileSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileSystemApplication.class,args);
    }
}
