package com.example.demo.rabbitmq;

import com.example.demo.dto.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerService {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(Notification notification) {
        rabbitTemplate.convertAndSend("loan-notifications", notification);
        System.out.println("Sent notification: " + notification);
    }
}
