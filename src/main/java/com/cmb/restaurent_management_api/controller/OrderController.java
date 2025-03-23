package com.cmb.restaurent_management_api.controller;

import com.cmb.restaurent_management_api.model.Order;
import com.cmb.restaurent_management_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/incoming")
    public List<Order> getIncomingOrders() {
        return orderService.getIncomingOrders();
    }

    @PostMapping("/{orderId}/accept")
    public ResponseEntity<Order> acceptOrder(@PathVariable Long orderId, @RequestParam String reason) {
        return ResponseEntity.ok(orderService.acceptOrder(orderId, reason));
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId, @RequestParam String reason) {
        return ResponseEntity.ok(orderService.cancelOrder(orderId, reason));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }
}
