package com.example.demo.controller;

import com.example.demo.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody String transactionData) {
        transactionService.processAndSendTransaction(transactionData);
        return ResponseEntity.ok("Transaction sent successfully");
    }
}
