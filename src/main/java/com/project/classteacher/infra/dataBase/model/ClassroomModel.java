package com.project.classteacher.infra.dataBase.model;

import com.project.classteacher.domain.entity.Classroom;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.UUID;

@Document(collection = "classroom")
@Data
@EqualsAndHashCode(of = "id")
@Builder
public class ClassroomModel {
    @MongoId
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
        return new Classroom(
                UUID.fromString(this.id),
                this.title,
                this.description,
                UUID.fromString(this.teacherId),
                this.dayDate
        );
    }
}
