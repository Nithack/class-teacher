package com.project.classteacher.infra.http.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorDTO {
    private Integer status;
    private String message;
}
