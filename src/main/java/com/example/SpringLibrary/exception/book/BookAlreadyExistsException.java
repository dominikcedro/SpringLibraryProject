package com.example.SpringLibrary.exception.book;

import com.example.SpringLibrary.exception.auth.EmailAlreadyExistingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookAlreadyExistsException extends RuntimeException{
    // based on isbn number we will check if it exists alreadu
    private BookAlreadyExistsException(String message) {
        super(message);
    }
    public static ResponseStatusException create(String isbn) {
        BookAlreadyExistsException exception = new BookAlreadyExistsException(String.format("Book with ISBN %s already exists", isbn));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}

