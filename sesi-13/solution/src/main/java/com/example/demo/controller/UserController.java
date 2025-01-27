package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.usecase.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String fullName,
                                           @RequestParam String email,
                                           @RequestParam(required = false) BigDecimal initialBalance) {
        return ResponseEntity.ok(userService.createUser(email, fullName, initialBalance));
    }
}
