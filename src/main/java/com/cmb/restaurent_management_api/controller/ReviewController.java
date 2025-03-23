package com.cmb.restaurent_management_api.controller;

import com.cmb.restaurent_management_api.model.Review;
import com.cmb.restaurent_management_api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/filter/rating")
    public List<Review> getReviewsByRating(@RequestParam int rating) {
        return reviewService.getReviewsByRating(rating);
    }

    @GetMapping("/filter/date")
    public List<Review> getReviewsByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return reviewService.getReviewsByDateRange(startDate, endDate);
    }

    @GetMapping("/aggregate-rating")
    public ResponseEntity<Double> getAggregateRating() {
        return ResponseEntity.ok(reviewService.getAggregateRating());
    }
}
