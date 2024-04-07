package com.example.SpringLibrary.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IncorrectPasswordException extends RuntimeException {
    private IncorrectPasswordException(String messsage) {
        super(messsage);
    }

    public static ResponseStatusException create() {
        IncorrectPasswordException exception = new IncorrectPasswordException("Incorrect password");
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.getMessage(), exception);
    }
}