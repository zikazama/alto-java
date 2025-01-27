package com.example.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "pelanggan", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Pelanggan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama tidak boleh kosong")
    @Column(nullable = false)
    private String nama;

    @Email(message = "Email tidak valid")
    @NotBlank(message = "Email tidak boleh kosong")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Double saldo = 0.0;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
