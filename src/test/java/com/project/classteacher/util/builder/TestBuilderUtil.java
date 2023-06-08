package com.project.classteacher.util.builder;

import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;

import java.util.UUID;

public class TestBuilderUtil {

    public static Teacher generateTeacher(UUID id, String name, String email, String password){
        return Teacher.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
    public static Secretary generateSecretary(UUID id, String name, String email, String password){
                // if id is not provided, it will be generated automatically

        return Secretary.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    public static UUID generateId() {
        return UUID.randomUUID();
    }
}
