package com.example.SpringLibrary.controller;

import com.example.SpringLibrary.dto.BookDTO;
import com.example.SpringLibrary.dto.ReviewDTO;
import com.example.SpringLibrary.dto.review.ReviewBookDTO;
import com.example.SpringLibrary.entity.Review;
import com.example.SpringLibrary.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB') or hasRole('ROLE_READER')")
    public Iterable<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB') or hasRole('ROLE_READER')")
    public Optional<Review> getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB') or hasRole('ROLE_READER')")
    public Review addReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.saveReview(reviewDTO);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB') or hasRole('ROLE_READER')")
    public Review updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        return reviewService.updateReview(id, reviewDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB') or hasRole('ROLE_READER')")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}