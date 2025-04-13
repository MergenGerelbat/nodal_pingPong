package com.nodalx.service1;

import com.nodalx.service1.model.Message;
import com.nodalx.service1.repository.MessageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private MessageRepository messageRepository;

    @RabbitListener(queues = "service1-pong-queue")
    public void receiveService2Pong(String message) {
        System.out.println("s1 received pong: " + message);
        saveMessage(message, "service2-pong");
    }

    @RabbitListener(queues = "service1-ping-queue")
    public void receiveService2Ping(String message) {
        System.out.println("s1 received ping: " + message);
        saveMessage(message, "service2-ping");
        
        messageSender.sendPong();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        messageSender.sendPing();
    }

    private void saveMessage(String content, String source) {
        Message message = new Message(content, source);
        Message savedMessage = messageRepository.save(message);
        System.out.println("Saved message to database with ID: " + savedMessage.getId());
    }
}