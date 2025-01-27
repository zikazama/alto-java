package com.example.transactionservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaksi")
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idPelanggan;

    @Column(nullable = false)
    private String jenisTransaksi; // "DEPOSIT" atau "WITHDRAW"

    @Min(value = 1, message = "Nominal harus lebih dari 0")
    @Column(nullable = false)
    private Double nominal;

    @Column(nullable = false)
    private LocalDateTime tanggalTransaksi;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(Long idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public LocalDateTime getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(LocalDateTime tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }
}
