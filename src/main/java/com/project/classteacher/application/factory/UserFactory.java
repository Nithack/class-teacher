package com.project.classteacher.application.factory;

import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.User;

import java.util.UUID;


public class UserFactory {

    public static User createUser(String name, String email, String password, Roles role) {
        return switch (role) {
            case TEACHER -> createTeacher(name, email, password);
            case SECRETARY -> createSecretary(name, email, password);
            default -> throw new IllegalArgumentException("Invalid role");
        };
    }
    private static Teacher createTeacher(String name, String email, String password){
        return Teacher.builder()
                .id(User.generateID())
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
    private static Secretary createSecretary(String name, String email, String password){
        return Secretary.builder()
                .id(User.generateID())
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
