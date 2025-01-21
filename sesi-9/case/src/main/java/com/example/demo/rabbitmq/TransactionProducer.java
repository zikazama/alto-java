package com.example.demo.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;
    private final KafkaTemplate<String, String> kafkaTemplate;

    // Constructor Injection
    public TransactionProducer(
        RabbitTemplate rabbitTemplate,
        KafkaTemplate<String, String> kafkaTemplate,
        @Value("${transaction.exchange}") String exchange,
        @Value("${transaction.routingKey}") String routingKey) {

        this.rabbitTemplate = rabbitTemplate;
        this.kafkaTemplate = kafkaTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    // Method to send message to RabbitMQ
    public void sendTransaction(String transactionMessage) {
        rabbitTemplate.convertAndSend(exchange, routingKey, transactionMessage);
        System.out.println("Transaction message sent to RabbitMQ: " + transactionMessage);
    }

    // Method to send message to Kafka
    public void sendTransactionKafka(String transaction) {
        kafkaTemplate.send("transaction-events", transaction);
        System.out.println("Transaction message sent to Kafka: " + transaction);
    }
}
