package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.config.decorators.ConfigContainersTest;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.project.classteacher.application.repository.ClassroomRepository;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("List Classroom By Teacher ID Test")
@ConfigContainersTest
final class ListClassroomByTeacherIDTest {

    @MockBean
    private ClassroomRepository classroomRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ListClassroomByTeacherID listClassroomByTeacherID;
    UUID DEFAULT_UUID;

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
                "2021-10-10T11:15:00.000Z",
                teacher.getId()
        );
        var classroomHistory = TestBuilderUtil.createClassroom(
                this.DEFAULT_UUID,
                "Historia",
                "Aula focada no ensino da historia",
                "2021-15-01T18:30:00.000Z",
                teacher.getId()
        );
        Mockito.when(userRepository.findTeacherById(teacher.getId())).thenReturn(teacher);
        Mockito.when(classroomRepository.listByTeacherId(teacher.getId())).thenReturn(List.of(new Classroom[]{classroomLiterature, classroomHistory}));
        var classroomSaved = listClassroomByTeacherID.execute(teacher.getId());

        assertAll("classroomSaved",
                () -> assertClassroom(classroomSaved.get(0), DEFAULT_UUID, "Literatura", "Aula focada no ensino da literatura", teacher.getId()),
                () -> assertClassroom(classroomSaved.get(1), DEFAULT_UUID, "Historia", "Aula focada no ensino da historia", teacher.getId())
        );
    }

    @Test()
    @DisplayName("should be throw exception when teacher not found")
    public void should_be_throw_exception_when_teacher_not_found() throws ParseException {

        Mockito.when(userRepository.findTeacherById(this.DEFAULT_UUID)).thenReturn(null);

        Assertions.assertThrows(TeacherNotFoundException.class, () -> {
            listClassroomByTeacherID.execute(this.DEFAULT_UUID);
        });
    }

    public void assertClassroom(Classroom classroom, UUID expectedId, String expectedTitle, String expectedDescription, UUID expectedTeacherId) {
        assertEquals(expectedId, classroom.getId());
        assertEquals(expectedTitle, classroom.getTitle());
        assertEquals(expectedDescription, classroom.getDescription());
        assertEquals(expectedTeacherId, classroom.getTeacherId());
    }
}
