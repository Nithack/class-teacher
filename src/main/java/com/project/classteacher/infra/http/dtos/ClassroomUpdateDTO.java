package com.project.classteacher.infra.http.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.classteacher.domain.entity.Classroom;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class ClassroomUpdateDTO {
    @Nullable
    private String title;
    @Nullable
    private String description;
    @Nullable
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
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
