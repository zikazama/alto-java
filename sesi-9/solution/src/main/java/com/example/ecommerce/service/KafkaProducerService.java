package com.example.ecommerce.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TOPIC = "orders";

    public void sendOrder(String orderJson) {
        kafkaTemplate.send(TOPIC, orderJson);
    }

    public String convertOrderToJson(Object order) throws JsonProcessingException {
        return objectMapper.writeValueAsString(order);
    }
}
