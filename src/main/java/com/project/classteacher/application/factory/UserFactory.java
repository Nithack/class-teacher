package com.project.classteacher.application.factory;

import com.project.classteacher.domain.entity.Password;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;

import java.util.Random;
import java.util.UUID;

public class UserFactory {

    public static User buildExistingUser(UUID id, String name, String email, String password, Roles role, String salt, Boolean approved) {
        return switch (role) {
            case TEACHER -> createTeacher(id, name, email, new Password(password, salt), approved);
            case SECRETARY -> createSecretary(id, name, email, new Password(password, salt), approved);
        };
    }

    public static User createUser(UUID id, String name, String email, String password, Roles role) {
        if (id == null) id = User.generateID();
        String RANDOM_SALT = String.valueOf(new Random().nextInt(1000));
        return switch (role) {
            case TEACHER -> createTeacher(id, name, email, Password.create(password, RANDOM_SALT), false);
            case SECRETARY -> createSecretary(id, name, email, Password.create(password, RANDOM_SALT), true);
        };
    }

    private static Teacher createTeacher(UUID id, String name, String email, Password password, Boolean approved) {
        return Teacher.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .approved(approved)
                .build();
    }

    private static Secretary createSecretary(UUID id, String name, String email, Password password, Boolean approved) {
        return Secretary.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .approved(approved)
                .build();
    }
}
