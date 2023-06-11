package com.project.classteacher.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
@Builder
public class Classroom {
    private UUID id;
    private String title;
    private String description;
    private UUID teacherId;
    private Date dayDate;

}
