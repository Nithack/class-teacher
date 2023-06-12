package com.project.classteacher.infra.http.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.classteacher.domain.enums.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Roles role;
    private Boolean approved;
}
