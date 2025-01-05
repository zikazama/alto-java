package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    // Getters and Setters
}