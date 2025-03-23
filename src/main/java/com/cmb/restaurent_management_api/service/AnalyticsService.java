package com.cmb.restaurent_management_api.service;

import com.cmb.restaurent_management_api.model.Order;
import com.cmb.restaurent_management_api.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private static final Logger logger = LoggerFactory.getLogger(AnalyticsService.class);

    @Autowired
    private OrderRepository orderRepository;

    public Double getDailyRevenue(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        List<Order> orders = orderRepository.findByOrderTimeBetween(start, end);
        return orders.stream().mapToDouble(o -> o.getPrice() * o.getQuantity()).sum();
    }

    public Double getWeeklyRevenue(LocalDate startDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = start.plusWeeks(1);
        List<Order> orders = orderRepository.findByOrderTimeBetween(start, end);
        return orders.stream().mapToDouble(o -> o.getPrice() * o.getQuantity()).sum();
    }

    public Double getMonthlyRevenue(LocalDate date) {
        LocalDateTime start = date.withDayOfMonth(1).atStartOfDay();
        LocalDateTime end = date.withDayOfMonth(date.lengthOfMonth()).atTime(LocalTime.MAX);
        List<Order> orders = orderRepository.findByOrderTimeBetween(start, end);
        return orders.stream().mapToDouble(o -> o.getPrice() * o.getQuantity()).sum();
    }

    public Map<String, Long> getPopularItems(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        List<Order> orders = orderRepository.findByOrderTimeBetween(start, end);
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getItem, Collectors.counting()));
    }

    public Map<Integer, Long> getPeakHours(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        List<Order> orders = orderRepository.findByOrderTimeBetween(start, end);
        return orders.stream()
                .collect(Collectors.groupingBy(o -> o.getOrderTime().getHour(), Collectors.counting()));
    }

    public Double getAveragePreparationTime(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        List<Order> orders = orderRepository.findByOrderTimeBetween(start, end);
        return orders.stream().mapToInt(Order::getPreparationTime).average().orElse(0);
    }
}
