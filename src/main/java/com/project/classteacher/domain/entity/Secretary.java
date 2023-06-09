package com.project.classteacher.domain.entity;

import com.project.classteacher.domain.enums.Roles;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@Getter
public class Secretary extends User {
    @Builder
    public Secretary(UUID id, String name, String email, Password password, Boolean approved) {
        super(id, name, email, password, Roles.valueOf("SECRETARY"), approved);
    }
}
