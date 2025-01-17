package com.example.kafkademo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @KafkaListener(topics = "example-topic", groupId = "my-group")
    public void consumeMessage(String message) {
        System.out.println("Pesan diterima: " + message);
    }
}
