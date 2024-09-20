package com.example.SpringEndApp.service;

import com.example.SpringEndApp.model.OrderModel;
import com.example.SpringEndApp.repository.OrderModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderModelRepository orderRepository;

    @Autowired
    public OrderService(OrderModelRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<OrderModel> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public OrderModel saveOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}