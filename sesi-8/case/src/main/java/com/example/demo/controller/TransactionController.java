package com.example.demo.controller;

import com.example.demo.service.TransactionProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionProducerService producerService;

    public TransactionController(TransactionProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody String transaction) {
        producerService.sendTransaction("transactions", transaction);
        return ResponseEntity.ok("Transaction sent to Kafka.");
    }
}
