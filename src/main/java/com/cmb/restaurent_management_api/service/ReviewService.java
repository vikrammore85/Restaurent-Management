package com.cmb.restaurent_management_api.service;

import com.cmb.restaurent_management_api.model.Review;
import com.cmb.restaurent_management_api.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByRating(int rating) {
        logger.info("Fetching reviews with rating: {}", rating);
        List<Review> reviews = reviewRepository.findByRating(rating);
        logger.info("Successfully fetched {} reviews with rating: {}", reviews.size(), rating);
        return reviews;
    }

    public List<Review> getReviewsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        logger.info("Fetching reviews between {} and {}", startDate, endDate);
        List<Review> reviews = reviewRepository.findByReviewDateBetween(startDate, endDate);
        logger.info("Successfully fetched {} reviews between {} and {}", reviews.size(), startDate, endDate);
        return reviews;
    }

    public double getAggregateRating() {
        logger.info("Calculating aggregate rating for all reviews");
        double aggregateRating = reviewRepository.findAll().stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
        logger.info("Aggregate rating calculated successfully: {}", aggregateRating);
        return aggregateRating;
    }
}