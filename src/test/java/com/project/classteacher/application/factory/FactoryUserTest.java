package com.project.classteacher.application.factory;

import com.project.classteacher.config.decorators.ConfigContainersTest;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.domain.entity.Secretary;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Factory User Test")
public class FactoryUserTest {

    @Test
    public void should_be_created_new_secretary() {

        var  teacher = UserFactory.createUser("Secretary", "Secretary@gmail.com", "123456", Roles.SECRETARY);

        assertEquals(teacher.getRole(), Roles.valueOf("SECRETARY"));
        assertEquals(teacher.getClass(), Secretary.class);
    }

    @Test
    public void should_be_created_new_teacher() {

        var  teacher = UserFactory.createUser("Teacher", "teacher@gmail.com", "123456", Roles.TEACHER);

        assertEquals(teacher.getRole(), Roles.valueOf("TEACHER"));
        assertEquals(teacher.getClass(), Teacher.class);
    }

}
