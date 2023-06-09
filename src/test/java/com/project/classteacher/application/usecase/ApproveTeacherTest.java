package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.repository.ClassroomRepository;
import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.config.decorators.ConfigContainersTest;
import com.project.classteacher.config.decorators.ConfigMongoDBTest;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.domain.entity.Teacher;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Teacher Approve Test")
@SpringBootTest(classes = ApproveTeacher.class)
public class ApproveTeacherTest{

    @MockBean
    private ClassroomRepository classroomRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ApproveTeacher approveTeacher;

    UUID DEFAULT_UUID;

    @BeforeEach
    public void setUp() {
        this.DEFAULT_UUID = TestBuilderUtil.generateId();
    }

    @Test
    @DisplayName("Should be approve teacher")
    public void should_be_created_new_teacher_with_id() {

        var teacher = TestBuilderUtil.createTeacher(
                this.DEFAULT_UUID,
                "Teacher 1",
                "teacher1@gmail.com",
                "123456"
        );

        Mockito.when(userRepository.findTeacherById(teacher.getId())).thenReturn(teacher);
        Mockito.when(userRepository.saveTeacher(Mockito.any(Teacher.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var teacherSaved = approveTeacher.execute(teacher.getId());

        assertEquals(teacherSaved.getId(), this.DEFAULT_UUID);
        assertEquals(teacherSaved.isApproved(), true);
    }

    @Test()
    @DisplayName("should be throw exception when teacher not found")
    public void should_be_throw_exception_when_teacher_not_found() throws ParseException {

        Mockito.when(userRepository.findTeacherById(this.DEFAULT_UUID)).thenReturn(null);

        Assertions.assertThrows(TeacherNotFoundException.class, () -> {
            approveTeacher.execute(this.DEFAULT_UUID);
        });
    }

}
