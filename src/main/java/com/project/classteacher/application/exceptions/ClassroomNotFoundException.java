package com.project.classteacher.application.exceptions;

import java.util.UUID;

public class ClassroomNotFoundException extends RuntimeException {

    public ClassroomNotFoundException(UUID id) {
        super("Classroom not found for id: " + id);
    }

    public ClassroomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}