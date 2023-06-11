package com.project.classteacher.infra.http.dtos;

import com.project.classteacher.domain.entity.Classroom;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class ClassroomOutputDTO {
    private UUID id;
    private String title;
    private String description;
    private Date dayDate;
    private UUID teacherId;

    public static ClassroomOutputDTO toDTO(Classroom classroom) {
        return ClassroomOutputDTO.builder()
                .id(classroom.getId())
                .title(classroom.getTitle())
                .description(classroom.getDescription())
                .dayDate(classroom.getDayDate())
                .teacherId(classroom.getTeacherId())
                .build();
    }
}
