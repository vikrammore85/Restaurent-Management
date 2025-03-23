package com.cmb.restaurent_management_api.service;

import com.cmb.restaurent_management_api.exception.OrderNotFoundException;
import com.cmb.restaurent_management_api.model.Order;
import com.cmb.restaurent_management_api.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getIncomingOrders() {
        logger.info("Fetching all incoming orders with status 'Pending'");
        List<Order> orders = orderRepository.findByStatus("Pending");
        logger.info("Successfully fetched {} incoming orders", orders.size());
        return orders;
    }

    public Order acceptOrder(Long orderId, String reason) {
        logger.info("Attempting to accept order with ID: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    logger.error("Order not found with ID: {}", orderId);
                    return new OrderNotFoundException("Order not found");
                });
        order.setStatus("Accepted");
        Order savedOrder = orderRepository.save(order);
        logger.info("Order with ID: {} has been successfully accepted", orderId);
        return savedOrder;
    }

    public Order cancelOrder(Long orderId, String reason) {
        logger.info("Attempting to cancel order with ID: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    logger.error("Order not found with ID: {}", orderId);
                    return new OrderNotFoundException("Order not found");
                });
        order.setStatus("Cancelled");
        order.setCancellationReason(reason);
        Order savedOrder = orderRepository.save(order);
        logger.info("Order with ID: {} has been successfully cancelled with reason: {}", orderId, reason);
        return savedOrder;
    }

    public Order updateOrderStatus(Long orderId, String status) {
        logger.info("Attempting to update status of order with ID: {} to {}", orderId, status);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    logger.error("Order not found with ID: {}", orderId);
                    return new OrderNotFoundException("Order not found");
                });
        order.setStatus(status);
        Order savedOrder = orderRepository.save(order);
        logger.info("Order with ID: {} has been successfully updated to status: {}", orderId, status);
        return savedOrder;
    }
}