package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.port.ClassroomPort;
import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.application.usecase.classroom.ListClassroomByTeacherID;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("List Classroom By Teacher ID Test")
@SpringBootTest(classes = ListClassroomByTeacherID.class)
final class ListClassroomByTeacherIDTest {

    UUID DEFAULT_UUID;
    @MockBean
    private ClassroomPort classroomPort;
    @MockBean
    private UserPort userPort;
    @Autowired
    private ListClassroomByTeacherID listClassroomByTeacherID;

    @BeforeEach
    public void setUp() {
        this.DEFAULT_UUID = TestBuilderUtil.generateId();
    }

    @Test
    @DisplayName("should be list all classroom by teacher id")
    public void should_be_list_all_classroom_by_teacher_id() throws ParseException {

        var teacher = TestBuilderUtil.generateTeacher();
        var classroomLiterature = TestBuilderUtil.createClassroom(
                this.DEFAULT_UUID,
                "Literatura",
                "Aula focada no ensino da literatura",
                new Date(),
                teacher.getId()
        );
        var classroomHistory = TestBuilderUtil.createClassroom(
                this.DEFAULT_UUID,
                "Historia",
                "Aula focada no ensino da historia",
                new Date(),
                teacher.getId()
        );
        Mockito.when(userPort.findById(teacher.getId())).thenReturn(teacher);
        Mockito.when(classroomPort.listByTeacherId(teacher.getId())).thenReturn(List.of(new Classroom[]{classroomLiterature, classroomHistory}));
        var classroomSaved = listClassroomByTeacherID.execute(teacher.getId());

        assertAll("classroomSaved",
                () -> assertClassroom(classroomSaved.get(0), DEFAULT_UUID, "Literatura", "Aula focada no ensino da literatura", teacher.getId()),
                () -> assertClassroom(classroomSaved.get(1), DEFAULT_UUID, "Historia", "Aula focada no ensino da historia", teacher.getId())
        );
    }

    @Test()
    @DisplayName("should be throw exception when teacher not found")
    public void should_be_throw_exception_when_teacher_not_found() {

        Mockito.when(userPort.findById(this.DEFAULT_UUID)).thenReturn(null);

        Assertions.assertThrows(TeacherNotFoundException.class, () -> listClassroomByTeacherID.execute(this.DEFAULT_UUID));
    }

    public void assertClassroom(Classroom classroom, UUID expectedId, String expectedTitle, String expectedDescription, UUID expectedTeacherId) {
        assertEquals(expectedId, classroom.getId());
        assertEquals(expectedTitle, classroom.getTitle());
        assertEquals(expectedDescription, classroom.getDescription());
        assertEquals(expectedTeacherId, classroom.getTeacherId());
    }
}
