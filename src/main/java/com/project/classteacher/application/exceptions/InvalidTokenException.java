package com.project.classteacher.application.exceptions;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String token) {
        super("Invalid token: " + token);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}