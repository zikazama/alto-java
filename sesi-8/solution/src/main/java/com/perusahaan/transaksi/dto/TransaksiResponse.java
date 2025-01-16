// src/main/java/com/perusahaan/transaksi/dto/TransaksiResponse.java
package com.perusahaan.transaksi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransaksiResponse {
    private String status;
    private String message;
}
