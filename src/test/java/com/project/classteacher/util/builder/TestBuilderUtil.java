package com.project.classteacher.util.builder;

import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.domain.entity.Password;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import org.springframework.beans.factory.annotation.Value;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

public class TestBuilderUtil {

    @Value("${basic.salt:salt}")
    static final String DEFAULT_SALT = "salt";

    public static Teacher generateTeacher(){
        return createTeacher(
                generateId(),
                "Secretary 1",
                "secretary1@gmail.com",
                "123456"
        );
    }

    public static Teacher createTeacher(UUID id, String name, String email, String password){
        return Teacher.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(Password.create(password, DEFAULT_SALT))
                .build();
    }
    public static Secretary generateSecretary(){
        return createSecretary(
                generateId(),
                "Teacher 1",
                "teacher1@gmail.com",
                "123456"
            );
    }
    public static Secretary createSecretary(UUID id, String name, String email, String password){
        return Secretary.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(Password.create(password, DEFAULT_SALT))
                .build();
    }
    public static UUID generateId() {
        return UUID.randomUUID();
    }

    public static Classroom generateClassroom() throws ParseException {
            return createClassroom(
                    generateId(),
                    "Classroom 1",
                    "Classroom 1 description",
                    Classroom.dateFormat("2021-01-01T00:00:00.000Z"),
                    generateId()
            );
    }

    public static Classroom createClassroom(UUID uuid, String title, String description, Date dayDate, UUID teacherId) {
        return new Classroom(
                uuid,
                title,
                description,
                teacherId,
                dayDate
        );
    }
}
