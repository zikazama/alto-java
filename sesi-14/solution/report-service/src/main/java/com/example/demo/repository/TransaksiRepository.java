package com.example.reportservice.repository;

import com.example.reportservice.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
    List<Transaksi> findAllByTanggalTransaksiBetween(LocalDateTime startDate, LocalDateTime endDate);
}
