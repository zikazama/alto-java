package com.example.transactionservice.service;

import com.example.transactionservice.model.Transaksi;
import com.example.transactionservice.repository.TransaksiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class TransaksiService {

    @Autowired
    private TransaksiRepository transaksiRepository;

    public Transaksi deposit(Long idPelanggan, Double nominal) {
        Transaksi transaksi = new Transaksi();
        transaksi.setIdPelanggan(idPelanggan);
        transaksi.setJenisTransaksi("DEPOSIT");
        transaksi.setNominal(nominal);
        transaksi.setTanggalTransaksi(LocalDateTime.now());
        return transaksiRepository.save(transaksi);
    }

    public Transaksi withdraw(Long idPelanggan, Double nominal, Double saldoSaatIni) {
        if (saldoSaatIni < nominal) {
            throw new IllegalArgumentException("Saldo tidak cukup untuk melakukan penarikan");
        }
        Transaksi transaksi = new Transaksi();
        transaksi.setIdPelanggan(idPelanggan);
        transaksi.setJenisTransaksi("WITHDRAW");
        transaksi.setNominal(nominal);
        transaksi.setTanggalTransaksi(LocalDateTime.now());
        return transaksiRepository.save(transaksi);
    }
}
