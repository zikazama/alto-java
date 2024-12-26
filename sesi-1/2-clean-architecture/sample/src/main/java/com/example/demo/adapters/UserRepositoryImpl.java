package com.example.demo.adapters;

import com.example.demo.domain.User;
import org.springframework.stereotype.Component;
import com.example.demo.repository.*;

@Component
public class UserRepositoryImpl implements UserRepository {
    // Simulasi penyimpanan database
    @Override
    public User save(User user) {
        // Simpan ke database (implementasi Spring JPA di sini)
        return user;
    }
}
