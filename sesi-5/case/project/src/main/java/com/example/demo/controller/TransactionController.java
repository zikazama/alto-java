package com.example.demo.controller;

import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public void performTransaction(@RequestBody Transaction transaction) {
        transactionService.performTransaction(transaction);
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactionsByAccount(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }
}