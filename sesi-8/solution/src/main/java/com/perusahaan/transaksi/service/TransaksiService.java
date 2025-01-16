// src/main/java/com/perusahaan/transaksi/service/TransaksiService.java
package com.perusahaan.transaksi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perusahaan.transaksi.config.RabbitMQConfig;
import com.perusahaan.transaksi.dto.TransaksiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransaksiService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void kirimTransaksi(TransaksiRequest transaksiRequest) throws JsonProcessingException {
        String transaksiJson = objectMapper.writeValueAsString(transaksiRequest);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                transaksiJson
        );
    }
}
