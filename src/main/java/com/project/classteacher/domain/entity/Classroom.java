package com.project.classteacher.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static Date dateParse(String dayDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return format.parse(dayDate);
    }

    public static String dateFormat(Date dayDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return format.format(dayDate);
    }
}
