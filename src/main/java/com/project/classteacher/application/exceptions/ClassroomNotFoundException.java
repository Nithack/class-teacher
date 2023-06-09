package com.project.classteacher.application.exceptions;

import java.util.UUID;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException(UUID id) {
        super("teacher not found for id: " + id);
    }

    public TeacherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}