package com.cmb.restaurent_management_api.controller;


import com.cmb.restaurent_management_api.service.AnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private static final Logger logger = LoggerFactory.getLogger(AnalyticsController.class);

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/daily-revenue")
    public Double getDailyRevenue(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        logger.info("Fetching daily revenue for date: {}", date);
        return analyticsService.getDailyRevenue(date);
    }

    @GetMapping("/weekly-revenue")
    public Double getWeeklyRevenue(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        logger.info("Fetching weekly revenue starting from: {}", startDate);
        return analyticsService.getWeeklyRevenue(startDate);
    }

    @GetMapping("/monthly-revenue")
    public Double getMonthlyRevenue(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        logger.info("Fetching monthly revenue for date: {}", date);
        return analyticsService.getMonthlyRevenue(date);
    }

    @GetMapping("/popular-items")
    public Map<String, Long> getPopularItems(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        logger.info("Fetching popular items for date: {}", date);
        return analyticsService.getPopularItems(date);
    }

    @GetMapping("/peak-hours")
    public Map<Integer, Long> getPeakHours(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        logger.info("Fetching peak hours for date: {}", date);
        return analyticsService.getPeakHours(date);
    }

    @GetMapping("/average-preparation-time")
    public Double getAveragePreparationTime(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        logger.info("Fetching average preparation time for date: {}", date);
        return analyticsService.getAveragePreparationTime(date);
    }
}
