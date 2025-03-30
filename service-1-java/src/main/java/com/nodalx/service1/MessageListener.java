package com.nodalx.service1;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private MessageSender messageSender;

    @RabbitListener(queues = "service1-pong-queue")
    public void receiveService2Pong(String message) {
        System.out.println("s1 received: " + message);
    }

    @RabbitListener(queues = "service1-ping-queue")
    public void receiveService2Ping(String message) {
        System.out.println("s1 received: " + message);
        messageSender.sendPong();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        messageSender.sendPing();
    }
}