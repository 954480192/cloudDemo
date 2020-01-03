package com.example.user.rabbitmq.consumer_confirms;

import com.example.user.rabbitmq.direct.DirectRabbitConfig;
import com.example.user.rabbitmq.direct.DirectReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *消费者确认
 * 消息接收的确认机制主要存在三种模式：
 * ①自动确认， 这也是默认的消息确认情况。   AcknowledgeMode.NONE
 * 一般这种情况我们都是使用try catch捕捉异常后，打印日志用于追踪数据，这样找出对应数据再做后续处理。
 *  ② 不确认， 这个不做介绍
 * ③ 手动确认  AcknowledgeMode.MANUAL
 * 消费者收到消息后，手动调用basic.ack/basic.nack/basic.reject后，RabbitMQ收到这些消息后，才认为本次投递成功。
 * basic.ack用于肯定确认 
 * basic.nack用于否定确认（注意：这是AMQP 0-9-1的RabbitMQ扩展） 
 * basic.reject用于否定确认，但与basic.nack相比有一个限制:一次只能拒绝单条消息 
 */
@Configuration
public class MessageListenerConfig {
 
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private Direct1Receiver directReceiver;//Direct消息接收处理类
//    @Autowired
//    FanoutReceiverA fanoutReceiverA;//Fanout消息接收处理类A
    @Autowired
    DirectRabbitOfConfrimsConfig directRabbitConfig;
//    @Autowired
//    FanoutRabbitConfig fanoutRabbitConfig;
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
        container.setQueues(directRabbitConfig.Test1DirectQueue());
        container.setMessageListener(directReceiver);
//        container.addQueues(fanoutRabbitConfig.queueA());
//        container.setMessageListener(fanoutReceiverA);
        return container;
    }
}