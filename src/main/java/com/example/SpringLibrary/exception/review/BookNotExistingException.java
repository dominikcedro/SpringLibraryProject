package com.example.SpringLibrary.exception.review;

import com.example.SpringLibrary.exception.auth.EmailAlreadyExistingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotExistingException extends RuntimeException{
    // based on isbn number we will check if it exists alreadu
    private BookNotExistingException(String message) {
        super(message);
    }
    public static ResponseStatusException create(Long id) {
        BookNotExistingException exception = new BookNotExistingException(String.format("Cannot create review for not existing book with id %d", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}

