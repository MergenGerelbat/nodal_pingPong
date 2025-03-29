package com.nodalx.service2

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    companion object {
        const val EXCHANGE_NAME = "ping-pong-exchange"
        const val SERVICE2_PING_QUEUE = "service2-ping-queue"
        const val SERVICE2_PONG_QUEUE = "service2-pong-queue"
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(EXCHANGE_NAME)
    }

    @Bean
    fun service2PingQueue(): Queue {
        return Queue(SERVICE2_PING_QUEUE, false)
    }

    @Bean
    fun service2PongQueue(): Queue {
        return Queue(SERVICE2_PONG_QUEUE, false)
    }

    @Bean
    fun service2PingBinding(service2PingQueue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(service2PingQueue).to(exchange).with("service2.ping.key")
    }

    @Bean
    fun service2PongBinding(service2PongQueue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(service2PongQueue).to(exchange).with("service2.pong.key")
    }
}