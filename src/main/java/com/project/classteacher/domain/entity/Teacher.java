package com.project.classteacher.domain.entity;

import com.project.classteacher.domain.enums.Roles;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;


@Getter
public class Teacher extends User{
    private Boolean approved;
    @Builder
    public Teacher(UUID id, String name, String email, Password password, Boolean approved) {
        super(id, name, email, password, Roles.valueOf("TEACHER"), approved);
        this.approved = false;
    }

    public Boolean isApproved(){
        return approved;
    }

    public void approve(){
        this.approved = true;
    }
}
