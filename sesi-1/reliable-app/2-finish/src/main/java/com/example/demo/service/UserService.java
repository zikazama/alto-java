package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getUsers() {
        return userRepository.getUsers();
    }

    public String addUser(String user) {
        userRepository.addUser(user);
        return "User added!";
    }

    public String deleteUser(String name) {
        userRepository.deleteUser(name);
        return "User deleted!";
    }
}
