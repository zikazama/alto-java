package com.example.demo.controllers;

import com.example.demo.domain.User;
import com.example.demo.usecases.RegisterUserUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping
    public User registerUser(@RequestBody User user) {
        return registerUserUseCase.execute(user);
    }
}
