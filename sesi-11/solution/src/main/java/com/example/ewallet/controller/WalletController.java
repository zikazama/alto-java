package com.example.ewallet.controller;

import com.example.ewallet.model.User;
import com.example.ewallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/topup")
    public ResponseEntity<String> topUp(@RequestParam String username, @RequestParam Double amount) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
        return ResponseEntity.ok("Top-up successful");
    }
}
