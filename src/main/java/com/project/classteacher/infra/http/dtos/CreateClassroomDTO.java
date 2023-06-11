package com.project.classteacher.infra.http.dtos;

import com.project.classteacher.domain.entity.Classroom;
import lombok.Builder;
import lombok.Data;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class CreateClassroomDTO {
    private String title;
    private String description;
    private Date dayDate;
    private UUID teacherId;

    public Classroom toDomain() throws ParseException {
        return Classroom.builder()
                .title(this.title)
                .description(this.description)
                .dayDate(this.dayDate)
                .teacherId(this.teacherId)
                .build();
    }
}
