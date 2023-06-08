package com.project.classteacher.application.usecase;

import com.project.classteacher.ConfigContainersTest;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.util.FakeUserRepository;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@ConfigContainersTest
public class SaveTeacherTest {

    private SaveTeacher saveTeacher = new SaveTeacher(new FakeUserRepository());

    @Test
    public void should_be_created_new_teacher_with_id() {

        var newTeacher = TestBuilderUtil.generateTeacher(
                "Teacher 1",
                "teacher1@gmail.com",
                "123456"
        );

        var teacherSaved = saveTeacher.execute(newTeacher);
        assertThat(teacherSaved.getId(), notNullValue());
    }

    @Test
    public void should_be_created_new_teacher_with_teacher_role() {

        var newTeacher = TestBuilderUtil.generateTeacher("Teacher 1", "teacher1@gmail.com", "123456");

        var teacherSaved = saveTeacher.execute(newTeacher);
        assertEquals(teacherSaved.getRole(), Roles.valueOf("TEACHER"));
    }

}
