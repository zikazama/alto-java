package com.financial.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    @KafkaListener(topics = "transactionTopic", groupId = "financial-app-group")
    public void listen(String message) {
        System.out.println("Received Kafka message: " + message);
    }
}
