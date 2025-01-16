package com.example.ecommerce.service;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "orders", groupId = "order_group")
    public void consume(String message) {
        try {
            Order order = objectMapper.readValue(message, Order.class);
            orderRepository.save(order);
            System.out.println("Order saved to DB: " + order);
        } catch (Exception e) {
            System.err.println("Failed to consume and save order: " + e.getMessage());
            // Anda dapat menambahkan penanganan lebih lanjut di sini
        }
    }
}
