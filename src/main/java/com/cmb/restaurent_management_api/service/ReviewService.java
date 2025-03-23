package com.cmb.restaurent_management_api.service;

import com.cmb.restaurent_management_api.model.Review;
import com.cmb.restaurent_management_api.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByRating(int rating) {
        return reviewRepository.findByRating(rating);
    }

    public List<Review> getReviewsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return reviewRepository.findByReviewDateBetween(startDate, endDate);
    }

    public double getAggregateRating() {
        return reviewRepository.findAll().stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
