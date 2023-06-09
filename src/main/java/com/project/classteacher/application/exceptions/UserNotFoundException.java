package com.project.classteacher.application.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UUID id) {
        super("teacher not found for id: " + id);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}