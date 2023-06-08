package com.project.classteacher.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@Getter
public class Secreatary extends User {
    @Builder
    public Secreatary(UUID id, String name, String email, String password) {
        super(id, name, email, password, Roles.valueOf("SECRETARY"));
    }
}
