package com.example.SpringLibrary.exception.loan;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanLimitExceededException extends RuntimeException {
    private LoanLimitExceededException(String message) {
        super(message);
    }

    public static ResponseStatusException create(Long userId) {
        LoanLimitExceededException exception = new LoanLimitExceededException(String.format("User with id %d has exceeded the loan limit", userId));
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
    }
}
