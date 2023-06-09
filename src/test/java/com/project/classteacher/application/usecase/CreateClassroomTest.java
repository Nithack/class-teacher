package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.repository.ClassroomRepository;
import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.config.decorators.ConfigContainersTest;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@DisplayName("Create Classroom Test")
@ConfigContainersTest
public class CreateClassroomTest {

    @MockBean
    private ClassroomRepository classroomRepository;

    @MockBean
    private UserRepository userRepository;


    @Autowired
    private CreateClassroom createClassroom;

    UUID DEFAULT_UUID;

    @BeforeEach
    public void setUp() {
        this.DEFAULT_UUID = TestBuilderUtil.generateId();
    }



    @Test
    @DisplayName("Should be create a classroom")
    public void should_be_create_a_classroom() throws ParseException {

        var teacher = TestBuilderUtil.generateTeacher(
                TestBuilderUtil.generateId(),
                "Teacher 1",
                "teacher1@gmail.com",
                "123456"
        );
        var classroomLiterature = TestBuilderUtil.generateClassroom(
                this.DEFAULT_UUID,
                "Literatura",
                "Aula focada no ensino da literatura",
                "2021-10-10T11:15:00.000Z",
                teacher.getId()
        );
        Mockito.when(userRepository.findTeacherById(teacher.getId())).thenReturn(teacher);
        Mockito.when(classroomRepository.save(classroomLiterature)).thenReturn(classroomLiterature);
        var classroomSaved = createClassroom.execute(classroomLiterature);

        assertAll(
                () -> assertEquals("ID",classroomLiterature.getId(), this.DEFAULT_UUID),
                () -> assertEquals("Title",classroomLiterature.getTitle(), classroomSaved.getTitle()),
                () -> assertEquals("Description",classroomLiterature.getDescription(), classroomSaved.getDescription()),
                () -> assertEquals("Teacher",classroomLiterature.getTeacherId(), classroomSaved.getTeacherId()),
                () -> assertEquals("Schedule",classroomLiterature.getDayDate(), classroomSaved.getDayDate())
        );
    }
    @Test
    @DisplayName("Should be throw exception when teacher not found")
    public void should_be_throw_exception_when_teacher_not_found() throws ParseException {

        var classroomLiterature = TestBuilderUtil.generateClassroom(
                this.DEFAULT_UUID,
                "Literatura",
                "Aula focada no ensino da literatura",
                "2021-10-10T11:15:00.000Z",
                TestBuilderUtil.generateId()
        );
        Mockito.when(userRepository.findTeacherById(this.DEFAULT_UUID)).thenReturn(null);
        Assertions.assertThrows(TeacherNotFoundException.class, () -> createClassroom.execute(classroomLiterature));
    }
}
