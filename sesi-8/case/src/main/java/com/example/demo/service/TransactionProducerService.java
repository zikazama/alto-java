package com.example.demo.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TransactionProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransaction(String topic, String transaction) {
        kafkaTemplate.send(topic, transaction);
    }
}
