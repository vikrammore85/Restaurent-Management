package com.cmb.restaurent_management_api.repository;

import com.cmb.restaurent_management_api.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRating(int rating);
    List<Review> findByReviewDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
