package com.project.classteacher.application.exceptions;

import java.util.UUID;

public class ClassroomNotFoundException extends RuntimeException {

    public ClassroomNotFoundException(UUID id) {
        super("teacher not found for id: " + id);
    }

    public ClassroomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}