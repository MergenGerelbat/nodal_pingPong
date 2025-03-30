package com.nodalx.service2

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.stereotype.Component

@Component
class MessageSender(private val rabbitTemplate: AmqpTemplate) {

    fun sendPing() {
        val message = "Ping from s2"
        println("Sending to s1: $message")
        rabbitTemplate.convertAndSend("ping-pong-exchange", "service1.ping.key", message)
    }

    fun sendPong() {
        val message = "Pong from s2"
        println("Sending to s1: $message")
        rabbitTemplate.convertAndSend("ping-pong-exchange", "service1.pong.key", message)
    }
} 