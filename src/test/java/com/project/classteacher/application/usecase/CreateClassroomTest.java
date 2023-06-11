package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.port.ClassroomAdapter;
import com.project.classteacher.application.port.UserAdapter;
import com.project.classteacher.application.usecase.classroom.CreateClassroom;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@DisplayName("Create Classroom Test")
@SpringBootTest(classes = CreateClassroom.class)
public class CreateClassroomTest {

    UUID DEFAULT_UUID;
    @MockBean
    private ClassroomAdapter classroomAdapter;
    @MockBean
    private UserAdapter userAdapter;
    @Autowired
    private CreateClassroom createClassroom;

    @BeforeEach
    public void setUp() {
        this.DEFAULT_UUID = TestBuilderUtil.generateId();
    }

    @Test
    @DisplayName("Should be create a classroom")
    public void should_be_create_a_classroom() throws ParseException {

        var teacher = TestBuilderUtil.generateTeacher();
        var classroomLiterature = TestBuilderUtil.createClassroom(
                this.DEFAULT_UUID,
                "Literatura",
                "Aula focada no ensino da literatura",
                Classroom.dateFormat("2021-10-10T11:15:00.000Z"),
                teacher.getId()
        );
        when(userAdapter.findById(teacher.getId())).thenReturn(teacher);
        when(classroomAdapter.save(classroomLiterature)).thenReturn(classroomLiterature);
        var classroomSaved = createClassroom.execute(classroomLiterature);

        assertAll(
                () -> assertEquals("ID", classroomLiterature.getId(), this.DEFAULT_UUID),
                () -> assertEquals("Title", classroomLiterature.getTitle(), classroomSaved.getTitle()),
                () -> assertEquals("Description", classroomLiterature.getDescription(), classroomSaved.getDescription()),
                () -> assertEquals("Teacher", classroomLiterature.getTeacherId(), classroomSaved.getTeacherId()),
                () -> assertEquals("Schedule", classroomLiterature.getDayDate(), classroomSaved.getDayDate()));
    }

    @Test
    @DisplayName("Should be throw exception when teacher not found")
    public void should_be_throw_exception_when_teacher_not_found() throws ParseException {

        var classroomLiterature = TestBuilderUtil.createClassroom(
                this.DEFAULT_UUID,
                "Literatura",
                "Aula focada no ensino da literatura",
                Classroom.dateFormat("2021-10-10T11:15:00.000Z"),
                TestBuilderUtil.generateId()
        );
        when(userAdapter.findById(this.DEFAULT_UUID)).thenReturn(null);
        assertThrows(TeacherNotFoundException.class, () -> createClassroom.execute(classroomLiterature));
    }
}
