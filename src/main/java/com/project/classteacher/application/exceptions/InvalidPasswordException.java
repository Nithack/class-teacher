package com.project.classteacher.application.exceptions;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String password) {
        super("Invalid Password" + password);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}