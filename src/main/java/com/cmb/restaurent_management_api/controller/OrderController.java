package com.cmb.restaurent_management_api.controller;

import com.cmb.restaurent_management_api.model.Order;
import com.cmb.restaurent_management_api.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping("/incoming")
    public List<Order> getIncomingOrders() {
        logger.info("Fetching all incoming orders");
        List<Order> incomingOrders = orderService.getIncomingOrders();
        logger.info("Successfully fetched {} incoming orders", incomingOrders.size());
        return incomingOrders;
    }

    @PostMapping("/{orderId}/accept")
    public ResponseEntity<Order> acceptOrder(@PathVariable Long orderId, @RequestParam String reason) {
        logger.info("Accepting order with ID: {}, reason: {}", orderId, reason);
        Order acceptedOrder = orderService.acceptOrder(orderId, reason);
        logger.info("Successfully accepted order: {}", acceptedOrder);
        return ResponseEntity.ok(acceptedOrder);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId, @RequestParam String reason) {
        logger.info("Canceling order with ID: {}, reason: {}", orderId, reason);
        Order canceledOrder = orderService.cancelOrder(orderId, reason);
        logger.info("Successfully canceled order: {}", canceledOrder);
        return ResponseEntity.ok(canceledOrder);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        logger.info("Updating status of order with ID: {} to {}", orderId, status);
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        logger.info("Successfully updated order status: {}", updatedOrder);
        return ResponseEntity.ok(updatedOrder);
    }
}
