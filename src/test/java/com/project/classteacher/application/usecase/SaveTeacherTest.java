package com.project.classteacher.application.usecase;

import com.project.classteacher.ConfigContainersTest;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.util.FakeUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(classes = SaveTeacher.class)
public class SaveTeacherTest {

    private Teacher teacher;

    @Autowired
    private FakeUserRepository userRepository;

    @Autowired
    private SaveTeacher saveTeacher;

    @Test
    @ConfigContainersTest
    public void shouldBeCreateNewTeacher() {

        var newTeacher = new Teacher("Andrey", "andrey@nithack.com", "1234567890", UUID.randomUUID());
        var teacherSaved = saveTeacher.execute(newTeacher);
        assertEquals(teacherSaved.getId(), notNullValue());

    }
}
