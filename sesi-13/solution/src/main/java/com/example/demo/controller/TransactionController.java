package com.example.demo.controller;

import com.example.demo.domain.Transaction;
import com.example.demo.usecase.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(transactionService.deposit(userId, amount));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(transactionService.withdraw(userId, amount));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestParam Long fromUserId,
                                                 @RequestParam Long toUserId,
                                                 @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(transactionService.transfer(fromUserId, toUserId, amount));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getTransactionsByUserId(userId));
    }
}
