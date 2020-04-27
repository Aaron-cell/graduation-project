package com.gp.resources_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author 码农界的小学生
 * @description:
 * @title: OkHttpConfig
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 2:48
 */
@ComponentScan
public class OkHttpConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
