package com.project.classteacher.infra.http.dtos;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {
    @Email
    private String email;
    private String password;
}
