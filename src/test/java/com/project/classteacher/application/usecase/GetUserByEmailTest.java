package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.UserNotFoundException;
import com.project.classteacher.application.repository.UserServiceRepository;
import com.project.classteacher.application.usecase.login.GetUserByEmail;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Get User By Email Test")
@SpringBootTest(classes = GetUserByEmail.class)
public class GetUserByEmailTest {

    UUID DEFAULT_UUID;
    @MockBean
    private UserServiceRepository userServiceRepository;
    @Autowired
    private GetUserByEmail getUserbyEmail;

    @BeforeEach
    public void setUp() {
        this.DEFAULT_UUID = TestBuilderUtil.generateId();
    }

    @Test
    @DisplayName("Should be return a teacher with information")
    public void should_be_return_a_teacher_with_information() {

        var newTeacher = TestBuilderUtil.createTeacher(
                this.DEFAULT_UUID,
                "Teacher 1",
                "teacher1@gmail.com",
                "123456"
        );

        Mockito.when(userServiceRepository.getByEmail(newTeacher.getEmail())).thenReturn(newTeacher);

        var teacherSaved = getUserbyEmail.execute(newTeacher.getEmail());

        assertEquals(teacherSaved.getId(), this.DEFAULT_UUID);
        assertEquals(teacherSaved.getName(), "Teacher 1");
        assertEquals(teacherSaved.getEmail(), "teacher1@gmail.com");
        assertTrue(teacherSaved.verifyPassword("123456"));
        assertEquals(teacherSaved.getRole(), Roles.TEACHER);
        assertEquals(teacherSaved.getClass(), Teacher.class);
    }

    @Test
    @DisplayName("Should be created new secretary with information")
    public void should_be_return_a_secretary_with_information() {

        var newSecretary = TestBuilderUtil.createSecretary(
                this.DEFAULT_UUID,
                "Teacher 1",
                "teacher1@gmail.com",
                "123456"
        );

        Mockito.when(userServiceRepository.getByEmail(newSecretary.getEmail())).thenReturn(newSecretary);

        var teacherSaved = getUserbyEmail.execute(newSecretary.getEmail());

        assertEquals(teacherSaved.getId(), this.DEFAULT_UUID);
        assertEquals(teacherSaved.getName(), "Teacher 1");
        assertEquals(teacherSaved.getEmail(), "teacher1@gmail.com");
        assertTrue(teacherSaved.verifyPassword("123456"));
        assertEquals(teacherSaved.getRole(), Roles.SECRETARY);
        assertEquals(teacherSaved.getClass(), Secretary.class);
    }

    @Test()
    @DisplayName("should be throw exception when teacher not found")
    public void should_be_throw_exception_when_teacher_not_found() throws UserNotFoundException {

        Mockito.when(userServiceRepository.findById(this.DEFAULT_UUID)).thenReturn(null);

        Assertions.assertThrows(UserNotFoundException.class, () -> getUserbyEmail.execute("teste@gmail.com"));
    }

}
