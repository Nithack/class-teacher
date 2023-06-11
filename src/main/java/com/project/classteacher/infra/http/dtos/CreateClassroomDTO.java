package com.project.classteacher.infra.http.dtos;

import com.project.classteacher.domain.entity.Classroom;
import lombok.Builder;
import lombok.Data;

import java.text.ParseException;
import java.util.UUID;

@Data
@Builder
public class CreateClassroomDTO {
    private String title;
    private String description;
    private String dayDate;
    private String teacherId;

    public Classroom toDomain() throws ParseException {
        return Classroom.builder()
                .title(this.title)
                .description(this.description)
                .dayDate(Classroom.dateParse(this.dayDate))
                .teacherId(UUID.fromString(this.teacherId))
                .build();
    }
}
