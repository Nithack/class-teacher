package com.project.classteacher.util.builder;

import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import java.text.ParseException;
import java.util.UUID;

public class TestBuilderUtil {

    public static Teacher generateTeacher(UUID id, String name, String email, String password){
        return Teacher.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
    public static Secretary generateSecretary(UUID id, String name, String email, String password){
        return Secretary.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    public static UUID generateId() {
        return UUID.randomUUID();
    }

    public static Classroom generateClassroom(UUID uuid, String title, String description, String dayDate, UUID teacherId) throws ParseException {
        return Classroom.builder()
                .id(uuid)
                .title(title)
                .description(description)
                .dayDate(Classroom.dateFormat(dayDate))
                .teacherId(teacherId)
                .build();
    }
}
