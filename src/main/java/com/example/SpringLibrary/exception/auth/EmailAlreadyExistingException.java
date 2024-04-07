package com.example.SpringLibrary.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyExistingException extends RuntimeException {
    private EmailAlreadyExistingException(String message) {
        super(message);
    }

    public static ResponseStatusException create(String email) {
        EmailAlreadyExistingException exception = new EmailAlreadyExistingException(String.format("User with email %s already exists", email));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}


