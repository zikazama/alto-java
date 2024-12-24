package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {

    private final List<String> users = new ArrayList<>(Arrays.asList("Alice", "Bob"));

    public List<String> getUsers() {
        return users;
    }

    public void addUser(String user) {
        users.add(user);
    }

    public void deleteUser(String name) {
        users.remove(name);
    }
}
