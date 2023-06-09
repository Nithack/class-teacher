package com.project.classteacher.application.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("user not found for email: " + email);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}