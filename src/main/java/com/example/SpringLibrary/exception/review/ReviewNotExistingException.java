package com.example.SpringLibrary.exception.review;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewNotExistingException extends RuntimeException{

    public ReviewNotExistingException(String message) {
        super(message);
    }

    public static ResponseStatusException create(Long reviewId) {
        ReviewNotExistingException exception = new ReviewNotExistingException(String.format("Review with id %d does not exist", reviewId));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}