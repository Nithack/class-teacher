package com.project.classteacher.application.factory;

import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.User;

import java.util.UUID;

public class UserFactory {

    public static User createUser(UUID id, String name, String email, String password, Roles role) {
        if(id == null) id = User.generateID();
        return switch (role) {
            case TEACHER -> createTeacher(id,name, email, password);
            case SECRETARY -> createSecretary(id,name, email, password);
        };
    }
    private static Teacher createTeacher(UUID id, String name, String email, String password){
        return Teacher.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
    private static Secretary createSecretary(UUID id, String name, String email, String password){
        return Secretary.builder()
                .id(User.generateID())
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
