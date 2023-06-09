package com.project.classteacher.domain.entity;

import com.project.classteacher.domain.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class User {
    private UUID id;
    private String name;
    private String email;
    private Password password;
    private Roles role;

    public static UUID generateID(){
        return UUID.randomUUID();
    }

    public String getPassword() {
        return password.getValue();
    }

    public Boolean verifyPassword(String password){
        return this.password.validate(password);
    }

    public String getSalt(){
        return password.getSalt();
    }
}

