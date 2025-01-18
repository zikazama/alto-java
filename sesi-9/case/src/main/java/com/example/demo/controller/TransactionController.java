package com.example.demo.controller;

import com.example.demo.service.TransactionService;
import com.example.demo.kafka.TransactionProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionProducer transactionProducer;

    // Constructor Injection (Semua dependency diinisialisasi)
    public TransactionController(TransactionService transactionService, TransactionProducer transactionProducer) {
        this.transactionService = transactionService;
        this.transactionProducer = transactionProducer;
    }

    @PostMapping("/send")
    public String sendTransaction(@RequestBody String transaction) {
        transactionProducer.sendTransaction(transaction); // Kirim data transaksi ke Kafka
        return "Transaction sent to Kafka!";
    }

    @PostMapping("/process")
    public ResponseEntity<String> createTransaction(@RequestBody String transactionData) {
        transactionService.processAndSendTransaction(transactionData); // Proses transaksi
        return ResponseEntity.ok("Transaction sent successfully");
    }
}
