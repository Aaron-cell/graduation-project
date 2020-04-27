package gp.center.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 码农界的小学生
 * @description:Eureka服务端
 * @title: EurekaApplication
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/16 20:36
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
