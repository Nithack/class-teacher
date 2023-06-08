package com.project.classteacher.domain.entity.factory;

import com.project.classteacher.domain.entity.Roles;
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
    public static Teacher createTeacher(String name, String email, String password){
        return Teacher.builder()
                .id(UUID.randomUUID())
                .name(name)
                .email("")
                .password("")
                .build();
    }
    public static Secretary createSecretary(String name, String email, String password){
        return Secretary.builder()
                .id(UUID.randomUUID())
                .name(name)
                .email("")
                .password("")
                .build();
    }
}
