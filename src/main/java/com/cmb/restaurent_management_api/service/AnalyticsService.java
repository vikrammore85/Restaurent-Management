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
        try {
            logger.info("Calculating daily revenue for date: {}", date);
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(LocalTime.MAX);
            List<Order> orders = orderRepository.findByOrderTimeBetween(start, end);
            Double revenue = orders.stream().mapToDouble(o -> o.getPrice() * o.getQuantity()).sum();
            logger.info("Daily revenue calculated successfully: {}", revenue);
            return revenue;
        } catch (Exception e) {
            logger.error("Error occurred while calculating daily revenue for date: {}", date, e);
            throw new RuntimeException("Failed to calculate daily revenue", e);
        }
    }

    public Double getWeeklyRevenue(LocalDate startDate) {
        try {
            logger.info("Calculating weekly revenue starting from date: {}", startDate);
            LocalDateTime start = startDate.atStartOfDay();
            LocalDateTime end = start.plusWeeks(1);
            List<Order> orders = orderRepository.findByOrderTimeBetween(start, end);
            Double revenue = orders.stream().mapToDouble(o -> o.getPrice() * o.getQuantity()).sum();
            logger.info("Weekly revenue calculated successfully: {}", revenue);
            return revenue;
        } catch (Exception e) {
            logger.error("Error occurred while calculating weekly revenue starting from date: {}", startDate, e);
            throw new RuntimeException("Failed to calculate weekly revenue", e);
        }
    }

    public Double getMonthlyRevenue(LocalDate date) {
        try {
            logger.info("Calculating monthly revenue for date: {}", date);
            LocalDateTime start = date.withDayOfMonth(1).atStartOfDay();
            LocalDateTime end = date.withDayOfMonth(date.lengthOfMonth()).atTime(LocalTime.MAX);
            List<Order> orders = orderRepository.findByOrderTimeBetween(start, end);
            Double revenue = orders.stream().mapToDouble(o -> o.getPrice() * o.getQuantity()).sum();
            logger.info("Monthly revenue calculated successfully: {}", revenue);
            return revenue;
        } catch (Exception e) {
            logger.error("Error occurred while calculating monthly revenue for date: {}", date, e);
            throw new RuntimeException("Failed to calculate monthly revenue", e);
        }
    }

    public Map<String, Long> getPopularItems(LocalDate date) {
        try {
            logger.info("Fetching popular items for date: {}", date);
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(LocalTime.MAX);
            List<Order> orders = orderRepository.findByOrderTimeBetween(start, end);
            Map<String, Long> popularItems = orders.stream()
                    .collect(Collectors.groupingBy(Order::getItem, Collectors.counting()));
            logger.info("Popular items fetched successfully: {}", popularItems);
            return popularItems;
        } catch (Exception e) {
            logger.error("Error occurred while fetching popular items for date: {}", date, e);
            throw new RuntimeException("Failed to fetch popular items", e);
        }
    }

    public Map<Integer, Long> getPeakHours(LocalDate date) {
        try {
            logger.info("Fetching peak hours for date: {}", date);
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(LocalTime.MAX);
            List<Order> orders = orderRepository.findByOrderTimeBetween(start, end);
            Map<Integer, Long> peakHours = orders.stream()
                    .collect(Collectors.groupingBy(o -> o.getOrderTime().getHour(), Collectors.counting()));
            logger.info("Peak hours fetched successfully: {}", peakHours);
            return peakHours;
        } catch (Exception e) {
            logger.error("Error occurred while fetching peak hours for date: {}", date, e);
            throw new RuntimeException("Failed to fetch peak hours", e);
        }
    }

    public Double getAveragePreparationTime(LocalDate date) {
        try {
            logger.info("Calculating average preparation time for date: {}", date);
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(LocalTime.MAX);
            List<Order> orders = orderRepository.findByOrderTimeBetween(start, end);
            Double averagePreparationTime = orders.stream().mapToInt(Order::getPreparationTime).average().orElse(0);
            logger.info("Average preparation time calculated successfully: {}", averagePreparationTime);
            return averagePreparationTime;
        } catch (Exception e) {
            logger.error("Error occurred while calculating average preparation time for date: {}", date, e);
            throw new RuntimeException("Failed to calculate average preparation time", e);
        }
    }
}