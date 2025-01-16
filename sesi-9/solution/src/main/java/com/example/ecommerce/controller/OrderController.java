package com.example.ecommerce.controller;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.service.KafkaProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping
    public ResponseEntity<String> createOrder(@Valid @RequestBody Order order) {
        try {
            String orderJson = kafkaProducerService.convertOrderToJson(order);
            kafkaProducerService.sendOrder(orderJson);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order created and sent to Kafka");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process order");
        }
    }
}
