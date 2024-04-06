package com.example.SpringLibrary.exception.review;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewAlreadyExistingException extends RuntimeException{
    // we will check if review with the same user id and book id exists
    private ReviewAlreadyExistingException(String message) {
        super(message);
    }
    public static ResponseStatusException create(Long userId, Long bookId) {
        ReviewAlreadyExistingException exception = new ReviewAlreadyExistingException(String.format("Review for user with id %d and book with id %d already exists", userId, bookId));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
