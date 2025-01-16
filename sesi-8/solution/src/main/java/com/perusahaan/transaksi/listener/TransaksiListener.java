// src/main/java/com/perusahaan/transaksi/listener/TransaksiListener.java
package com.perusahaan.transaksi.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perusahaan.transaksi.dto.TransaksiRequest;
import com.perusahaan.transaksi.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransaksiListener {

    private static final Logger logger = LoggerFactory.getLogger(TransaksiListener.class);
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void menerimaTransaksi(String transaksiJson) {
        try {
            TransaksiRequest transaksi = objectMapper.readValue(transaksiJson, TransaksiRequest.class);
            // Proses transaksi di sini (misalnya, simpan ke database)
            logger.info("Menerima dan memproses transaksi: {}", transaksi);
            // Simulasi proses
            Thread.sleep(1000);
            logger.info("Transaksi {} berhasil diproses.", transaksi.getIdTransaksi());
        } catch (Exception e) {
            logger.error("Gagal memproses transaksi: {}", e.getMessage());
        }
    }
}
