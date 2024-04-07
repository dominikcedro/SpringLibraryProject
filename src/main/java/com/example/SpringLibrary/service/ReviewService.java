package com.example.SpringLibrary.service;

import com.example.SpringLibrary.dto.BookDTO;
import com.example.SpringLibrary.dto.ReviewDTO;
import com.example.SpringLibrary.entity.Book;
import com.example.SpringLibrary.entity.Review;
import com.example.SpringLibrary.entity.User;
import com.example.SpringLibrary.exception.review.ReviewAlreadyExistingException;
import com.example.SpringLibrary.exception.review.ReviewNotExistingException;
import com.example.SpringLibrary.repository.BookRepository;
import com.example.SpringLibrary.repository.ReviewRepository;
import com.example.SpringLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// exception handling
import com.example.SpringLibrary.exception.review.BookNotExistingException;

import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }
    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
    public Review saveReview(ReviewDTO reviewDTO) {
        Optional<Book> existingBook = bookRepository.findById(reviewDTO.getBookId());
        if(existingBook.isEmpty()){
            throw BookNotExistingException.create(reviewDTO.getBookId());
        }

        // check if review for this book id and user id already exists
        Optional<Review> existingReview = reviewRepository.findByBookBookIdAndUserId(reviewDTO.getBookId(), reviewDTO.getUserId());
        if(existingReview.isPresent()){
            throw ReviewAlreadyExistingException.create(reviewDTO.getBookId(), reviewDTO.getUserId());
        }

        Review review = new Review();

        User user = userRepository.findById(reviewDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        review.setUser(user);

        review.setBook(existingBook.get());

        review.setContent(reviewDTO.getContent());
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, ReviewDTO reviewDTO) {

        Review review = reviewRepository.findById(id).orElseThrow(() -> ReviewNotExistingException.create(id));

        User user = userRepository.findById(reviewDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        review.setUser(user);

        Book book = bookRepository.findById(reviewDTO.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
        review.setBook(book);

        review.setContent(reviewDTO.getContent());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}