package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<String> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public String addUser(@RequestBody String user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{name}")
    public String deleteUser(@PathVariable String name) {
        return userService.deleteUser(name);
    }
}
