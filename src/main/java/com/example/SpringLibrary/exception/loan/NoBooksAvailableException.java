package com.example.SpringLibrary.exception.loan;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoBooksAvailableException extends RuntimeException {
    public NoBooksAvailableException(String message) {
        super(message);
    }

    public static ResponseStatusException create(String bookName) {

        NoBooksAvailableException exception = new NoBooksAvailableException(String.format("No more available copies of book %s available", bookName));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
