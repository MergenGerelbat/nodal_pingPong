package com.nodalx.service2

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class MessageListener(private val messageSender: MessageSender) {

    @RabbitListener(queues = ["service2-pong-queue"])
    fun receiveService1Pong(message: String) {
        println("Service 2 received: $message")
    }

    @RabbitListener(queues = ["service2-ping-queue"])
    fun receiveService1Ping(message: String) {
        println("Service 2 received: $message")
        messageSender.sendPong()

        Thread.sleep(10000) // wait 10 seconds
        messageSender.sendPing()
    }
}