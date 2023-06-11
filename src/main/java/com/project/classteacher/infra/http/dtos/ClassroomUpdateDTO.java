package com.project.classteacher.infra.http.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.classteacher.domain.entity.Classroom;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassroomUpdateDTO {
    @Nullable
    private String title;
    @Nullable
    private String description;
    @Nullable
    private Date dayDate;
    @Nullable
    private UUID teacherId;

    public Classroom toDomain() {
        return Classroom.builder()
                .title(this.title)
                .description(this.description)
                .dayDate(this.dayDate)
                .teacherId(this.teacherId)
                .build();
    }
}
