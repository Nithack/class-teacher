package com.project.classteacher.application.factory;

import com.project.classteacher.domain.entity.Password;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class UserFactory {
    private static final Random rando_salt = new SecureRandom();

    private UserFactory() throws NoSuchAlgorithmException {
        throw new NoSuchAlgorithmException("This class cannot be instantiated");
    }

    public static User buildExistingUser(UUID id, String name, String email, String password, Roles role, String salt, Boolean approved) {
        return switch (role) {
            case TEACHER -> createTeacher(id, name, email, new Password(password, salt), approved);
            case SECRETARY -> createSecretary(id, name, email, new Password(password, salt), approved);
        };
    }

    public static User createUser(UUID id, String name, String email, String password, Roles role) {
        if (id == null) id = User.generateID();

        return switch (role) {
            case TEACHER -> createTeacher(id, name, email, Password.create(password, rando_salt.toString()), false);
            case SECRETARY -> createSecretary(id, name, email, Password.create(password, rando_salt.toString()), true);
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
