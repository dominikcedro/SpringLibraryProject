package com.example.SpringLibrary.exception.loan;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanAlreadyExistingException extends RuntimeException{

    private LoanAlreadyExistingException(String message) {
        super(message);
    }

    public static ResponseStatusException create(Long userId, Long bookId) {
        LoanAlreadyExistingException exception = new LoanAlreadyExistingException(String.format("Loan for user with id %d and book with id %d already exists", userId, bookId));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}