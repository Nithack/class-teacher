package com.project.classteacher.infra.http.controller.handler;

import com.project.classteacher.application.exceptions.ClassroomNotFoundException;
import com.project.classteacher.application.exceptions.InvalidTokenException;
import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.exceptions.UserNotFoundException;
import com.project.classteacher.infra.http.dtos.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(ClassroomNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleClassroomNotFoundException(ClassroomNotFoundException exception) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .status(404)
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorDTO> handleInvalidTokenException(InvalidTokenException exception) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .status(401)
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleTeacherNotFoundException(TeacherNotFoundException exception) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .status(404)
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .status(404)
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception exception) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .status(400)
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
    }

}
