package com.example.demo.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanApplicationProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "loan-applications";

    public void sendLoanApplication(String loanApplication) {
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, loanApplication);
        kafkaTemplate.send(record);
    }
}
