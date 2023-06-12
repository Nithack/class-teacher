package com.project.classteacher.application.exceptions;

import java.util.UUID;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException(UUID id) {
        super("Teacher not found for id: " + id);
    }

    public TeacherNotFoundException(String message) {
        super(message);
    }

    public TeacherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}