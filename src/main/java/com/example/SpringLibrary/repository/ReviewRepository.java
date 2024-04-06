package com.example.SpringLibrary.repository;

import com.example.SpringLibrary.entity.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for Review entity.
 */
public interface ReviewRepository extends CrudRepository<Review, Long> {

    /**
     * Deletes all reviews associated with a given book ID.
     *
     * @param bookId the ID of the book whose reviews are to be deleted
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Review r WHERE r.book.id = ?1")
    void deleteByBookId(Long bookId);
}