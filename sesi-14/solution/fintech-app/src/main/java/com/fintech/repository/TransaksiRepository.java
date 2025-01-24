package com.fintech.repository;

import com.fintech.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransaksiRepository extends JpaRepository<Transaksi, UUID> {

    // Mendapatkan semua transaksi berdasarkan idPelanggan dan rentang waktu
    List<Transaksi> findByPelangganIdAndTanggalBetween(UUID idPelanggan, LocalDateTime startDate, LocalDateTime endDate);
}
