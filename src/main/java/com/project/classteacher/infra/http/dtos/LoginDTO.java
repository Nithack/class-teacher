package com.project.classteacher.infra.http.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {
    private String email;
    private String password;
}