package com.fintech.service;

import com.fintech.model.Pelanggan;
import com.fintech.model.Transaksi;
import com.fintech.repository.PelangganRepository;
import com.fintech.repository.TransaksiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransaksiService {

    private final TransaksiRepository transaksiRepository;
    private final PelangganRepository pelangganRepository;

    public TransaksiService(TransaksiRepository transaksiRepository, PelangganRepository pelangganRepository) {
        this.transaksiRepository = transaksiRepository;
        this.pelangganRepository = pelangganRepository;
    }

    @Transactional
    public Transaksi buatTransaksi(UUID idPelanggan, String jenisTransaksi, double nominal) {
        Pelanggan pelanggan = pelangganRepository.findById(idPelanggan)
                .orElseThrow(() -> new IllegalArgumentException("Pelanggan tidak ditemukan"));

        if ("Penarikan".equalsIgnoreCase(jenisTransaksi) && pelanggan.getSaldo() < nominal) {
            throw new IllegalArgumentException("Saldo tidak mencukupi untuk penarikan");
        }

        if ("Deposit".equalsIgnoreCase(jenisTransaksi)) {
            pelanggan.setSaldo(pelanggan.getSaldo() + nominal);
        } else if ("Penarikan".equalsIgnoreCase(jenisTransaksi)) {
            pelanggan.setSaldo(pelanggan.getSaldo() - nominal);
        }

        Transaksi transaksi = new Transaksi(pelanggan, jenisTransaksi, nominal, LocalDateTime.now());
        pelangganRepository.save(pelanggan);
        return transaksiRepository.save(transaksi);
    }

    public List<Transaksi> getTransaksiByPelanggan(UUID idPelanggan, LocalDateTime startDate, LocalDateTime endDate) {
        return transaksiRepository.findByPelangganIdAndTanggalBetween(idPelanggan, startDate, endDate);
    }
}
