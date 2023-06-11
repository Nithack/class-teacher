package com.project.classteacher.domain.entity;

import com.project.classteacher.domain.enums.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DecodeToken {
    private UUID id;
    private String name;
    private String email;
    private Roles role;
    private Boolean approved;
}
