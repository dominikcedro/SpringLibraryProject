package com.example.SpringLibrary.exception.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotExistingException extends RuntimeException{
    private BookNotExistingException(String message) {
        super(message);
    }
    public static ResponseStatusException create(String id) {
        BookNotExistingException exception = new BookNotExistingException(String.format("Book with ID %s does not exist", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}