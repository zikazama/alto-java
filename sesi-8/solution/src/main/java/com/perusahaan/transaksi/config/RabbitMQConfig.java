// src/main/java/com/perusahaan/transaksi/config/RabbitMQConfig.java
package com.perusahaan.transaksi.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "transaksi.queue";
    public static final String EXCHANGE_NAME = "transaksi.exchange";
    public static final String ROUTING_KEY = "transaksi.routingKey";

    @Bean
    public Queue transaksiQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange transaksiExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue transaksiQueue, TopicExchange transaksiExchange) {
        return BindingBuilder.bind(transaksiQueue).to(transaksiExchange).with(ROUTING_KEY);
    }
}
