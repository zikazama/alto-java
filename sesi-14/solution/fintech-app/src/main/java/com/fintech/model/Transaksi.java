package com.fintech.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idTransaksi;

    @ManyToOne
    @JoinColumn(name = "id_pelanggan", nullable = false)
    private Pelanggan pelanggan;

    private String jenisTransaksi; // "Deposit" atau "Penarikan"
    private double nominal;
    private LocalDateTime tanggal;

    public Transaksi() {}

    public Transaksi(Pelanggan pelanggan, String jenisTransaksi, double nominal, LocalDateTime tanggal) {
        this.pelanggan = pelanggan;
        this.jenisTransaksi = jenisTransaksi;
        this.nominal = nominal;
        this.tanggal = tanggal;
    }

    public UUID getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(UUID idTransaksi) { this.idTransaksi = idTransaksi; }

    public Pelanggan getPelanggan() { return pelanggan; }
    public void setPelanggan(Pelanggan pelanggan) { this.pelanggan = pelanggan; }

    public String getJenisTransaksi() { return jenisTransaksi; }
    public void setJenisTransaksi(String jenisTransaksi) { this.jenisTransaksi = jenisTransaksi; }

    public double getNominal() { return nominal; }
    public void setNominal(double nominal) { this.nominal = nominal; }

    public LocalDateTime getTanggal() { return tanggal; }
    public void setTanggal(LocalDateTime tanggal) { this.tanggal = tanggal; }
}
