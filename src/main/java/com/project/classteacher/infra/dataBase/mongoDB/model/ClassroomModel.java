package com.project.classteacher.infra.dataBase.mongoDB.model;

import com.project.classteacher.domain.entity.Classroom;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "classroom")
@Data
@EqualsAndHashCode(of = "id")
@Builder
public class ClassroomModel {
    @Id
    private String id;
    private String title;
    private String description;
    private String teacherId;
    private Date dayDate;

    public static ClassroomModel toModel(Classroom classroom) {
        return ClassroomModel.builder()
                .id(classroom.getId().toString())
                .title(classroom.getTitle())
                .description(classroom.getDescription())
                .teacherId(classroom.getTeacherId().toString())
                .dayDate(classroom.getDayDate())
                .build();
    }

    public Classroom toDomain() {
        return Classroom.builder()
                .id(UUID.fromString(this.id))
                .title(this.title)
                .description(this.description)
                .teacherId(UUID.fromString(this.teacherId))
                .dayDate(this.dayDate)
                .build();
    }
}
