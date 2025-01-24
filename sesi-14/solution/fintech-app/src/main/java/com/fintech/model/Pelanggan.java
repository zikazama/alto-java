package com.fintech.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Pelanggan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nama;
    private String email;
    private double saldo;

    // Constructor, Getters, and Setters
    public Pelanggan() {}

    public Pelanggan(String nama, String email, double saldo) {
        this.nama = nama;
        this.email = email;
        this.saldo = saldo;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}
