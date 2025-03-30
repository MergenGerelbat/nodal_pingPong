package com.nodalx.service1;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "ping-pong-exchange";
    public static final String SERVICE_1_PING_QUEUE = "service1-ping-queue";
    public static final String SERVICE_1_PONG_QUEUE = "service1-pong-queue";

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue service1PingQueue() {
        return new Queue(SERVICE_1_PING_QUEUE, false);
    }

    @Bean
    Queue service1PongQueue() {
        return new Queue(SERVICE_1_PONG_QUEUE, false);
    }

    @Bean
    Binding service1PingBinding(Queue service1PingQueue, TopicExchange exchange) {
        return BindingBuilder.bind(service1PingQueue).to(exchange).with("service1.ping.key");
    }

    @Bean
    Binding service1PongBinding(Queue service1PongQueue, TopicExchange exchange) {
        return BindingBuilder.bind(service1PongQueue).to(exchange).with("service1.pong.key");
    }
}