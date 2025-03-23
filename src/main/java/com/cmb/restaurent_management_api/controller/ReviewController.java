package com.cmb.restaurent_management_api.controller;

import com.cmb.restaurent_management_api.model.Review;
import com.cmb.restaurent_management_api.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);


    @Autowired
    private ReviewService reviewService;

    @GetMapping("/filter/rating")
    public List<Review> getReviewsByRating(@RequestParam int rating) {
        logger.info("Fetching reviews with rating: {}", rating);
        List<Review> reviews = reviewService.getReviewsByRating(rating);
        logger.info("Successfully fetched {} reviews with rating: {}", reviews.size(), rating);
        return reviews;
    }

    @GetMapping("/filter/date")
    public List<Review> getReviewsByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        logger.info("Fetching reviews between {} and {}", startDate, endDate);
        List<Review> reviews = reviewService.getReviewsByDateRange(startDate, endDate);
        logger.info("Successfully fetched {} reviews between {} and {}", reviews.size(), startDate, endDate);
        return reviews;
    }

    @GetMapping("/aggregate-rating")
    public ResponseEntity<Double> getAggregateRating() {
        logger.info("Fetching aggregate rating of all reviews");
        Double aggregateRating = reviewService.getAggregateRating();
        logger.info("Successfully fetched aggregate rating: {}", aggregateRating);
        return ResponseEntity.ok(aggregateRating);
    }
}
