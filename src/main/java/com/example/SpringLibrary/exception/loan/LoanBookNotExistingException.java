package com.example.SpringLibrary.exception.loan;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanBookNotExistingException extends RuntimeException{
    private LoanBookNotExistingException(String message) {
        super(message);
    }
    public static ResponseStatusException create(Long bookId) {
        LoanBookNotExistingException exception = new LoanBookNotExistingException(String.format("Book with id %d does not exist", bookId));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}
