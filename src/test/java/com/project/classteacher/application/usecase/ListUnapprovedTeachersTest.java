package com.project.classteacher.application.usecase;

import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.application.usecase.teacher.ListUnapprovedTeachers;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("List Classroom By Teacher ID Test")
@SpringBootTest(classes = ListUnapprovedTeachers.class)
final class ListUnapprovedTeachersTest {

    UUID DEFAULT_UUID;
    @MockBean
    private UserPort userPort;
    @Autowired
    private ListUnapprovedTeachers listUnapprovedTeachers;

    @BeforeEach
    void setUp() {
        this.DEFAULT_UUID = TestBuilderUtil.generateId();
    }

    @Test
    @DisplayName("should be list all classroom by teacher id")
    void should_be_list_all_classroom_by_teacher_id() {

        var teacherUnapprovedOne = TestBuilderUtil.generateTeacher();
        var teacherUnapprovedTwo = TestBuilderUtil.generateTeacher();

        Mockito.when(userPort.listByApprovedAndRole(false, Roles.TEACHER)).thenReturn(List.of(teacherUnapprovedOne, teacherUnapprovedTwo));

        var unapprovedTeachers = listUnapprovedTeachers.execute();

        assertAll("classroomSaved",
                () -> assertTeacher(unapprovedTeachers.get(0), teacherUnapprovedOne),
                () -> assertTeacher(unapprovedTeachers.get(1), teacherUnapprovedTwo)
        );
    }

    @Test()
    @DisplayName("should return null when teachers not found")
    void should_return_null_when_teacher_not_found() {

        Mockito.when(userPort.findTeacherById(this.DEFAULT_UUID)).thenReturn(null);

        assertTrue(listUnapprovedTeachers.execute().isEmpty());
    }

    private void assertTeacher(Teacher teacher, Teacher teacherExpected) {
        assertAll("teacher",
                () -> assertEquals(teacher.getId(), teacherExpected.getId()),
                () -> assertEquals(teacher.getName(), teacherExpected.getName()),
                () -> assertEquals(teacher.getEmail(), teacherExpected.getEmail()),
                () -> assertEquals(teacher.getRole(), teacherExpected.getRole()),
                () -> assertEquals(teacher.getApproved(), teacherExpected.getApproved())
        );
    }
}
