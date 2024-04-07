package com.example.SpringLibrary.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotExistingException extends RuntimeException{
    private UserNotExistingException(String messsage){
        super(messsage);
    }

    public static ResponseStatusException create(String username){
        UserNotExistingException exception = new UserNotExistingException(String.format("User with username %s does not exist", username));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}