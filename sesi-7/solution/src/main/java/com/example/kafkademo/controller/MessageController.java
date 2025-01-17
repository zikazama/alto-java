package com.example.kafkademo.controller;

import com.example.kafkademo.dto.MessageDTO;
import com.example.kafkademo.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {
    private final ProducerService producerService;

    public MessageController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody MessageDTO messageDTO) {
        producerService.sendMessage(messageDTO.getMessage());
        return ResponseEntity.ok("Pesan berhasil dikirim");
    }
}
