package com.project.classteacher.infra.http.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private Integer status;
    private String message;
}
