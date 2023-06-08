package com.project.classteacher.application.usecase;

import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.config.decorators.ConfigContainersTest;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.util.FakeUserRepository;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;




@ConfigContainersTest
public class SaveTeacherTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private SaveTeacher saveTeacher;

    @Test
    @DisplayName("Should be created new teacher with id")
    public void should_be_created_new_teacher_with_id() {

        var newTeacher = TestBuilderUtil.generateTeacher(
                "Teacher 1",
                "teacher1@gmail.com",
                "123456"
        );
        Mockito.when(userRepository.saveTeacher(newTeacher)).thenReturn(newTeacher);
        var teacherSaved = saveTeacher.execute(newTeacher);
        assertThat(teacherSaved.getId(), notNullValue());
    }

    @Test
    @DisplayName("Should be created new teacher with teacher role")
    public void should_be_created_new_teacher_with_teacher_role() {

        var newTeacher = TestBuilderUtil.generateTeacher("Teacher 1", "teacher1@gmail.com", "123456");
        Mockito.when(userRepository.saveTeacher(newTeacher)).thenReturn(newTeacher);
        var teacherSaved = saveTeacher.execute(newTeacher);
        assertEquals(teacherSaved.getRole(), Roles.valueOf("TEACHER"));
    }

}
