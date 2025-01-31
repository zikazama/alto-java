package com.example.walletservice.controller;

import com.example.walletservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{userId}/balance")
    public Map<String, Object> getBalance(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("balance", walletService.getBalance(userId));
        return response;
    }

    @PostMapping("/{userId}/topup")
    public Map<String, String> topUp(@PathVariable Long userId, @RequestBody Map<String, String> request) {
        BigDecimal amount = new BigDecimal(request.get("amount"));
        walletService.topUp(userId, amount);
        return Map.of("message", "Top-up successful");
    }

    @PostMapping("/{userId}/deduct")
    public Map<String, String> deduct(@PathVariable Long userId, @RequestBody Map<String, String> request) {
        BigDecimal amount = new BigDecimal(request.get("amount"));
        walletService.deductBalance(userId, amount);
        return Map.of("message", "Deduction successful");
    }
}
