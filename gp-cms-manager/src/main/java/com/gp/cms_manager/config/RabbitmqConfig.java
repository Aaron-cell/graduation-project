package com.gp.cms_manager.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 码农界的小学生
 * @description:
 * @title: RabbitmqConfig
 * @projectName xc-edu
 * @description: TODO
 * @date 2019/11/8 20:27
 */
@Configuration
public class RabbitmqConfig {
    //交换机的名称
    public static final String GP_ROUTING_CMS_EXCHANGE = "gp_routing_cms_exchange";
    //队列 重置密码验证码
    @Value("${gp.mq.sms_queue_reset}")
    public String sms_queue_reset;
    @Value("${gp.mq.sms_routing_reset}")
    public String sms_routing_reset;
    //队列 重置密码发送密码
    @Value("${gp.mq.sms_queue_password}")
    public String sms_queue_password;
    @Value("${gp.mq.sms_routing_password}")
    public String sms_routing_password;
    //队列 生日定时提醒
    @Value("${gp.mq.email_queue_birth}")
    public String email_queue_birth;
    @Value("${gp.mq.email_routing_birth}")
    public String email_routing_birth;
    /**
     * 容器中注入的bean名为ex_routing_cms_postpage
     * 交换机名为ex_routing_cms_postpage 类型为direct 即路由模式
     * durable：是否持久 默认true
     * @return
     */
    @Bean(GP_ROUTING_CMS_EXCHANGE)
    public Exchange EXCHANGE_TOPICS_INFORM(){
        return ExchangeBuilder.directExchange(GP_ROUTING_CMS_EXCHANGE).durable(true).build();
    }
    //声明队列
    @Bean("sms_queue_reset")
    public Queue QUEUE_PROCESSTASK() {
        Queue queue = new Queue(sms_queue_reset,true,false,false);
        return queue;
    }
    //声明队列
    @Bean("sms_queue_password")
    public Queue QUEUE_PROCESSTASK01() {
        Queue queue = new Queue(sms_queue_password,true,false,false);
        return queue;
    }
    //声明队列
    @Bean("email_queue_birth")
    public Queue QUEUE_PROCESSTASK02() {
        Queue queue = new Queue(email_queue_birth,true,false,false);
        return queue;
    }
    /**
     * 绑定队列到交换机 .
     * @param queue    the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding binding_queue_media_processtask(@Qualifier("sms_queue_reset") Queue queue, @Qualifier(GP_ROUTING_CMS_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(sms_routing_reset).noargs();
    }
    @Bean
    public Binding binding_queue_media_processtask01(@Qualifier("sms_queue_password") Queue queue, @Qualifier(GP_ROUTING_CMS_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(sms_routing_password).noargs();
    }
    /**
     * 绑定队列到交换机 .
     * @param queue    the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding binding_queue_media_processtask02(@Qualifier("email_queue_birth") Queue queue, @Qualifier(GP_ROUTING_CMS_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(email_routing_birth).noargs();
    }
}
