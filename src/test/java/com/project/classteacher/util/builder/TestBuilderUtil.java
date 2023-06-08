package com.project.classteacher.util.factory;

import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;

import java.util.UUID;

public class TestBuilderUtil {

    public static Teacher generateTeacher(String name, String email, String password){
        return Teacher.builder()
                .id(UUID.randomUUID())
                .name(name)
                .email("")
                .password("")
                .build();
    }
    public static Secretary generateSecretary(String name, String email, String password){
        return Secretary.builder()
                .id(UUID.randomUUID())
                .name(name)
                .email("")
                .password("")
                .build();
    }
}
