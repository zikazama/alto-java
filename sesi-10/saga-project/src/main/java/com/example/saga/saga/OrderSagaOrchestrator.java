package com.example.saga.saga;

import com.example.saga.event.OrderEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderSagaOrchestrator {

    private final OrderEventPublisher eventPublisher;

    public OrderSagaOrchestrator(OrderEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void startSaga(Long orderId) {
        eventPublisher.publishOrderCreated(orderId);
        // Anda bisa menambahkan logika kompensasi jika diperlukan.
    }
}
