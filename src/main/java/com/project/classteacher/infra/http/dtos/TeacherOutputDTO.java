package com.project.classteacher.infra.http.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.enums.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherOutputDTO {
    private UUID id;
    private String name;
    private String email;
    private Roles role;
    private Boolean approved;

    public static TeacherOutputDTO toDTO(Teacher teacher) {
        return TeacherOutputDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .role(teacher.getRole())
                .approved(teacher.getApproved())
                .build();
    }

    public static List<TeacherOutputDTO> toDTO(List<Teacher> teachers) {
        return teachers.stream()
                .map(TeacherOutputDTO::toDTO)
                .toList();
    }
}
