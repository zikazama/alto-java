package com.example.saga.service;

import com.example.saga.dto.OrderRequest;
import com.example.saga.model.Order;
import com.example.saga.repository.OrderRepository;
import com.example.saga.saga.OrderSagaOrchestrator;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderSagaOrchestrator sagaOrchestrator;

    public OrderService(OrderRepository orderRepository, OrderSagaOrchestrator sagaOrchestrator) {
        this.orderRepository = orderRepository;
        this.sagaOrchestrator = sagaOrchestrator;
    }

    public String createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setProductId(orderRequest.getProductId());
        order.setStatus("PENDING");
        orderRepository.save(order);
        sagaOrchestrator.startSaga(order.getId());
        return "Order Created: " + order.getId();
    }
}
