package gp.security.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author 码农界的小学生
 * @description:security认证与授权
 * @title: AuthApplication
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/16 21:09
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
//扫描一下包中注解 本项目下默认自动扫描
@EntityScan("com.gp.framework.domain.resource")//扫描实体类
@ComponentScan(basePackages = {"com.gp.api","com.gp.framework","gp.security.oauth2"})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
