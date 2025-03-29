package com.nodalx.service2

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.stereotype.Component

@Component
class MessageSender(private val rabbitTemplate: AmqpTemplate) {

    fun sendPing() {
        val message = "Ping from service2"
        println("Sending to Service 1: $message")
        rabbitTemplate.convertAndSend("ping-pong-exchange", "service1.ping.key", message)
    }

    fun sendPong() {
        val message = "Pong from service2"
        println("Sending to Service 1: $message")
        rabbitTemplate.convertAndSend("ping-pong-exchange", "service1.pong.key", message)
    }
} 