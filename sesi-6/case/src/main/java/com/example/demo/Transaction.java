package com.example.demo;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private Double amount;
    private LocalDateTime timestamp;

    public Transaction(String id, Double amount, LocalDateTime timestamp) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    // Getter dan Setter
}