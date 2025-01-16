// src/main/java/com/perusahaan/transaksi/dto/TransaksiRequest.java
package com.perusahaan.transaksi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransaksiRequest {
    @NotBlank(message = "ID Transaksi tidak boleh kosong")
    private String idTransaksi;

    @NotBlank(message = "Nama Pengirim tidak boleh kosong")
    private String namaPengirim;

    @NotBlank(message = "Nama Penerima tidak boleh kosong")
    private String namaPenerima;

    @NotNull(message = "Jumlah tidak boleh kosong")
    @Min(value = 1, message = "Jumlah harus lebih dari 0")
    private Double jumlah;
}
