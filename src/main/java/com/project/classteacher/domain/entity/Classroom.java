package com.project.classteacher.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
public class Classroom {
    private UUID id;
    private String title;
    private String description;
    private UUID teacherId;
    private Date dayDate;

    public Classroom(UUID id, String title, String description, UUID teacherId, String dayDate) throws ParseException {
        this.id = id;
        this.title = title;
        this.description = description;
        this.teacherId = teacherId;
        this.dayDate = dateFormat(dayDate);
    }
    public static Date dateFormat(String dayDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss.SSS'Z'");
        return format.parse(dayDate);
    }
}
