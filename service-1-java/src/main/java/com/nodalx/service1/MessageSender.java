package com.nodalx.service1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class MessageSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        sendPing();
    }

    public void sendPing() {
        String message = "ping from s1";
        System.out.println("Sending to s2: " + message);
        rabbitTemplate.convertAndSend("ping-pong-exchange", "service2.ping.key", message);
    }

    public void sendPong() {
        String message = "pong from s1";
        System.out.println("Sending to s2: " + message);
        rabbitTemplate.convertAndSend("ping-pong-exchange", "service2.pong.key", message);
    }
}