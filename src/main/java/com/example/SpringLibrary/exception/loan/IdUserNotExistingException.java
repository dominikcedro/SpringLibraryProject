package com.example.SpringLibrary.exception.loan;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IdUserNotExistingException extends RuntimeException{

    private IdUserNotExistingException(String message) {
        super(message);
    }

    public static ResponseStatusException create(Long userId) {
        IdUserNotExistingException exception = new IdUserNotExistingException(String.format("User with id %d does not exist", userId));
    return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
}
}
