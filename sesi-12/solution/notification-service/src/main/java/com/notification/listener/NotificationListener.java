package com.notification.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @RabbitListener(queues = "transactionQueue")
    public void handleTransactionMessage(String message) {
        // Log pesan yang diterima
        System.out.println("Received message from transactionQueue: " + message);

        // Implementasi notifikasi
        sendNotification(message);
    }

    private void sendNotification(String message) {
        // Logika pengiriman notifikasi (email, SMS, atau lainnya)
        System.out.println("Sending notification: " + message);
    }
}
