package com.project.classteacher.infra.http.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisteredUserDTO {
    private String name;
    private String email;
    private String token;
    private Roles role;
    private UUID id;
    private Boolean approved;

    public static RegisteredUserDTO toDTO(User user, Token token) {
        return RegisteredUserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .token(token.getValue())
                .role(user.getRole())
                .approved(user.getApproved())
                .build();
    }
}
