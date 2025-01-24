package com.fintech.repository;

import com.fintech.model.Pelanggan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PelangganRepository extends JpaRepository<Pelanggan, UUID> {}
