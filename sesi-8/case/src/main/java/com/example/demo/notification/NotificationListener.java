package com.example.demo.notification;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @RabbitListener(queues = "loan-notifications")
    public void receiveNotification(String notificationMessage) {
        System.out.println("Notifikasi diterima: " + notificationMessage);
    }
}
