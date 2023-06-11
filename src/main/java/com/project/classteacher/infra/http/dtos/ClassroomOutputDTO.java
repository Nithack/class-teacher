package com.project.classteacher.infra.http.dtos;

import com.project.classteacher.domain.entity.Classroom;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassroomOutputDTO {
    private String id;
    private String title;
    private String description;
    private String dayDate;
    private String teacherId;

    public static ClassroomOutputDTO toDTO(Classroom classroom) {
        return ClassroomOutputDTO.builder()
                .id(classroom.getId().toString())
                .title(classroom.getTitle())
                .description(classroom.getDescription())
                .dayDate(Classroom.dateFormat(classroom.getDayDate()))
                .teacherId(classroom.getTeacherId().toString())
                .build();
    }
}
