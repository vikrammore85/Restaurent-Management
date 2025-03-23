package com.cmb.restaurent_management_api.service;

import com.cmb.restaurent_management_api.exception.OrderNotFoundException;
import com.cmb.restaurent_management_api.model.Order;
import com.cmb.restaurent_management_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getIncomingOrders() {
        return orderRepository.findByStatus("Pending");
    }

    public Order acceptOrder(Long orderId, String reason) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.setStatus("Accepted");
        return orderRepository.save(order);
    }

    public Order cancelOrder(Long orderId, String reason) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.setStatus("Cancelled");
        order.setCancellationReason(reason);
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
