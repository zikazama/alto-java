package com.example.demo.rabbitmq;

import com.example.demo.dto.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerService {
    @RabbitListener(queues = "loan-notifications")
    public void processNotification(Notification notification) {
        System.out.println("Processing notification: " + notification);
        // Add notification sending logic (e.g., email/SMS)
    }
}
