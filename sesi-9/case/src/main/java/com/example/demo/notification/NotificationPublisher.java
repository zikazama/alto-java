package com.example.demo.notification;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String QUEUE_NAME = "loan-notifications";

    public void sendNotification(String notificationMessage) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, notificationMessage);
    }
}
