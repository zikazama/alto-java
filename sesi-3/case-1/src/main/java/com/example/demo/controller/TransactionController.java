package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.transaction.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionContext transactionContext;

    @Autowired
    public TransactionController(TransactionContext transactionContext) {
        this.transactionContext = transactionContext;
    }

    @PostMapping("/{type}")
    public void processTransaction(@PathVariable String type, @RequestBody Transaction transaction) {
        transactionContext.executeTransaction(type, transaction);
    }
}