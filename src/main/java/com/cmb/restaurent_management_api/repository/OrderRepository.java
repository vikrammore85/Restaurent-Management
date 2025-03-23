package com.cmb.restaurent_management_api.repository;

import com.cmb.restaurent_management_api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(String status);
    List<Order> findByOrderTimeBetween(LocalDateTime start, LocalDateTime end);


}
