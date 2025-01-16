// src/main/java/com/bank/digitalbank/service/UserService.java
package com.bank.digitalbank.service;

import com.bank.digitalbank.model.User;
import com.bank.digitalbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Mendapatkan saldo pengguna dengan caching
    @Cacheable(value = "userBalance", key = "#username")
    public Double getUserBalance(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            return userOpt.get().getBalance();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Memperbarui saldo pengguna dan menghapus cache terkait
    @CacheEvict(value = "userBalance", key = "#username")
    public void updateUserBalance(String username, Double newBalance) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setBalance(newBalance);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
