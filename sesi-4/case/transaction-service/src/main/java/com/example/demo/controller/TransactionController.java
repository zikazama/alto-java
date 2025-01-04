package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @PostMapping("/transactions")
    public String processTransaction(@RequestBody String transaction) {
        return "Transaction processed";
    }
}