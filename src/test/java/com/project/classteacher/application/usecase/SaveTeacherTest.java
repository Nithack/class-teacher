package com.project.classteacher.application.usecase;

import com.project.classteacher.application.repository.ClassroomRepository;
import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.config.decorators.ConfigContainersTest;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;




@DisplayName("Save Teacher Test")
@ConfigContainersTest
public class SaveTeacherTest {

    @MockBean
    private ClassroomRepository classroomRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private SaveTeacher saveTeacher;

    UUID DEFAULT_UUID;

    @BeforeEach
    public void setUp() {
        this.DEFAULT_UUID = TestBuilderUtil.generateId();
    }

    @Test
    @DisplayName("Should be created new teacher with information")
    public void should_be_created_new_teacher_with_id() {

        var newTeacher = TestBuilderUtil.generateTeacher(
                this.DEFAULT_UUID,
                "Teacher 1",
                "teacher1@gmail.com",
                "123456"
        );
        Mockito.when(userRepository.saveTeacher(newTeacher)).thenReturn(newTeacher);
        var teacherSaved = saveTeacher.execute(newTeacher);
        assertEquals(teacherSaved.getId(), this.DEFAULT_UUID);
        assertEquals(teacherSaved.getName(), "Teacher 1");
        assertEquals(teacherSaved.getEmail(), "teacher1@gmail.com");
        assertEquals(teacherSaved.getPassword(), "123456");
    }

    @Test
    @DisplayName("Should be created new teacher with teacher role")
    public void should_be_created_new_teacher_with_teacher_role() {

        var newTeacher = TestBuilderUtil.generateTeacher(
                this.DEFAULT_UUID,
                "Teacher 1",
                "teacher1@gmail.com",
                "123456"
        );
        Mockito.when(userRepository.saveTeacher(newTeacher)).thenReturn(newTeacher);
        var teacherSaved = saveTeacher.execute(newTeacher);
        assertEquals(teacherSaved.getRole(), Roles.valueOf("TEACHER"));
    }

}
