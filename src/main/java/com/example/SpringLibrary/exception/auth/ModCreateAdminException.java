package com.example.SpringLibrary.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ModCreateAdminException extends RuntimeException{

    private ModCreateAdminException(String messsage) {
        super(messsage);
    }

    public static ResponseStatusException create(String currentRole, String creationRole) {
        ModCreateAdminException exception = new ModCreateAdminException("Invalid role creation: " + currentRole + " cannot create " + creationRole + " users");
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.getMessage(), exception);
    }
}
