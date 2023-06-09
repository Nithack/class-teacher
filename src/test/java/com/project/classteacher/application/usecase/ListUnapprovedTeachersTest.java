package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.config.decorators.ConfigContainersTest;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.enums.Roles;
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
final class ListUnapprovedTeachersTest {

    @MockBean
    private ClassroomRepository classroomRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ListUnapprovedTeachers listUnapprovedTeachers;
    UUID DEFAULT_UUID;

    @BeforeEach
    public void setUp() {
        this.DEFAULT_UUID = TestBuilderUtil.generateId();
    }

    @Test
    @DisplayName("should be list all classroom by teacher id")
    public void should_be_list_all_classroom_by_teacher_id() throws ParseException {

        var teacherUnapprovedOne = TestBuilderUtil.generateTeacher();
        var teacherUnapprovedTwo = TestBuilderUtil.generateTeacher();

        Mockito.when(userRepository.listByApprovedAndRole(false, Roles.TEACHER)).thenReturn(List.of(new Teacher[]{teacherUnapprovedOne, teacherUnapprovedTwo}));

        var unapprovedTeachers = listUnapprovedTeachers.execute();

        assertAll("classroomSaved",
                () -> assertTeacher(unapprovedTeachers.get(0), teacherUnapprovedOne),
                () -> assertTeacher(unapprovedTeachers.get(1), teacherUnapprovedTwo)
        );
    }

    @Test()
    @DisplayName("should be throw exception when teacher not found")
    public void should_be_throw_exception_when_teacher_not_found() throws ParseException {

        Mockito.when(userRepository.findTeacherById(this.DEFAULT_UUID)).thenReturn(null);

        Assertions.assertThrows(TeacherNotFoundException.class, () -> {
            listUnapprovedTeachers.execute();
        });
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
