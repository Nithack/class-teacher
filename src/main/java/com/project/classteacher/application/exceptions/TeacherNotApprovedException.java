package com.project.classteacher.application.exceptions;

import java.util.UUID;

public class TeacherNotApprovedException extends RuntimeException {

    public TeacherNotApprovedException(UUID id) {
        super("Teacher not approved by id: " + id);
    }

    public TeacherNotApprovedException(String message, Throwable cause) {
        super(message, cause);
    }
}