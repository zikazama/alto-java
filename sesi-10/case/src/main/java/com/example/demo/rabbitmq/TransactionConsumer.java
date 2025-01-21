package com.example.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class TransactionConsumer {

    @RabbitListener(queues = "transactionQueue")
    public void receiveTransaction(String transactionMessage) {
        System.out.println("Transaction message received: " + transactionMessage);
        processTransaction(transactionMessage);
    }

    private void processTransaction(String transactionMessage) {
        // Logika pemrosesan transaksi
        System.out.println("Processing transaction: " + transactionMessage);
    }
}
