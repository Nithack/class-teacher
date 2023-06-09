package com.project.classteacher.application.factory;

import com.project.classteacher.domain.entity.Password;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.User;
import java.util.Random;
import java.util.UUID;

public class UserFactory {

    public static User buildExistingUser(UUID id, String name, String email, String password, Roles role, String salt) {
        return switch (role) {
            case TEACHER -> createTeacher(id,name, email, new Password(password, salt));
            case SECRETARY -> createSecretary(id,name, email, new Password(password, salt));
        };
    }
    public static User createUser(UUID id, String name, String email, String password, Roles role) {
        if(id == null) id = User.generateID();
        String RANDOM_SALT = new Random().toString();
        return switch (role) {
            case TEACHER -> createTeacher(id,name, email, Password.create(password, RANDOM_SALT));
            case SECRETARY -> createSecretary(id,name, email, Password.create(password, RANDOM_SALT));
        };
    }
    private static Teacher createTeacher(UUID id, String name, String email, Password password){
        return Teacher.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
    private static Secretary createSecretary(UUID id, String name, String email, Password password){
        return Secretary.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
