package com.project.classteacher.infra.http.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.classteacher.application.util.CustomDateDeserializer;
import com.project.classteacher.domain.entity.Classroom;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class CreateClassroomDTO {
    private String title;
    private String description;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date dayDate;
    private UUID teacherId;

    public Classroom toDomain() {
        return Classroom.builder()
                .id(UUID.randomUUID())
                .title(this.title)
                .description(this.description)
                .dayDate(this.dayDate)
                .teacherId(this.teacherId)
                .build();
    }

}
