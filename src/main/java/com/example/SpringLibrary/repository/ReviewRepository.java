package com.example.SpringLibrary.repository;

import com.example.SpringLibrary.entity.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repository interface for Review entity.
 */
public interface ReviewRepository extends CrudRepository<Review, Long> {

    /**
     * Deletes all reviews associated with a given book ID.
     *
     */
    Optional<Review> findByBookBookIdAndUserId(Long bookId, Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Review r WHERE r.book.bookId = ?1")
    void deleteByBookId(Long bookId);
}