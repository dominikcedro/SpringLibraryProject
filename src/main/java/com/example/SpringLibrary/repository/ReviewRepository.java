package com.example.SpringLibrary.repository;

import com.example.SpringLibrary.entity.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}