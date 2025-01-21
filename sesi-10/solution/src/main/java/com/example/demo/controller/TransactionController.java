package com.example.transactionsaga.controller;

import com.example.transactionsaga.service.TransactionOrchestrator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionOrchestrator orchestrator;

    public TransactionController(TransactionOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> processTransaction(
            @RequestParam Long accountIdFrom,
            @RequestParam Long accountIdTo,
            @RequestParam double amount) {
        String result = orchestrator.processTransaction(accountIdFrom, accountIdTo, amount);
        if ("success".equals(result)) {
            return ResponseEntity.ok(Map.of("status", "success", "message", "Transaction completed successfully."));
        }
        return ResponseEntity.ok(Map.of("status", "failed", "message", "Transaction failed. Rolled back successfully."));
    }
}
