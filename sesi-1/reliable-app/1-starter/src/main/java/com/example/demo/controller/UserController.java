package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private List<String> users = new ArrayList<>(Arrays.asList("Alice", "Bob"));

    @GetMapping
    public List<String> getUsers() {
        return users;
    }

    @PostMapping
    public String addUser(@RequestBody String user) {
        users.add(user);
        return "User added!";
    }

    @DeleteMapping("/{name}")
    public String deleteUser(@PathVariable String name) {
        users.remove(name);
        return "User deleted!";
    }
}
