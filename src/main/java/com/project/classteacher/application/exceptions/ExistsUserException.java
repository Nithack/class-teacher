package com.project.classteacher.application.exceptions;

public class ExistsUserException extends RuntimeException {

    public ExistsUserException(String user) {
        super("User exist in database: " + user);
    }

    public ExistsUserException(String message, Throwable cause) {
        super(message, cause);
    }
}