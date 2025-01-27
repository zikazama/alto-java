package com.example.userservice.repository;

import com.example.userservice.model.Pelanggan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PelangganRepository extends JpaRepository<Pelanggan, Long> {
    boolean existsByEmail(String email);
}
