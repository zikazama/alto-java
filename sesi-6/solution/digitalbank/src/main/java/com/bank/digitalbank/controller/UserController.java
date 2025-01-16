// src/main/java/com/bank/digitalbank/controller/UserController.java
package com.bank.digitalbank.controller;

import com.bank.digitalbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// DTO untuk permintaan pembaruan saldo
class UpdateBalanceRequest {
    private String username;
    private Double newBalance;

    // Getters dan Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Double newBalance) {
        this.newBalance = newBalance;
    }
}

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint untuk mendapatkan saldo pengguna
    @GetMapping("/{username}/balance")
    public ResponseEntity<Double> getUserBalance(@PathVariable String username) {
        try {
            Double balance = userService.getUserBalance(username);
            return ResponseEntity.ok(balance);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint untuk memperbarui saldo pengguna
    @PutMapping("/balance")
    public ResponseEntity<String> updateUserBalance(@RequestBody UpdateBalanceRequest request) {
        try {
            userService.updateUserBalance(request.getUsername(), request.getNewBalance());
            return ResponseEntity.ok("Balance updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
