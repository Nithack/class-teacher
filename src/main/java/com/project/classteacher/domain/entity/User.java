package com.project.classteacher.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class User {
    private UUID id = generateId();
    private String name;
    private String email;
    private String password;
    private Roles role;

    UUID generateId(){
        return UUID.randomUUID();
    }
}

