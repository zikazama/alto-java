package com.example.demo.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public TransactionProducer(RabbitTemplate rabbitTemplate, 
                               @Value("${transaction.exchange}") String exchange,
                               @Value("${transaction.routingKey}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void sendTransaction(String transactionMessage) {
        rabbitTemplate.convertAndSend(exchange, routingKey, transactionMessage);
        System.out.println("Transaction message sent: " + transactionMessage);
    }
}
