package com.example.user.rabbitmq.consumer_confirms;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitOfConfrimsConfig {
 
    //队列 起名：TestDirectQueue
    @Bean
    public Queue Test1DirectQueue() {
        return new Queue("Test1DirectQueue",true);  //true 是否持久
    }
 
    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange Test1DirectExchange() {
        return new DirectExchange("test1Exchange");
    }
 
    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(Test1DirectQueue()).to(Test1DirectExchange()).with("test1");
    }
}