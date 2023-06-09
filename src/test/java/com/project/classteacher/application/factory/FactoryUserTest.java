package com.project.classteacher.application.factory;

import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.domain.entity.Secretary;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Factory User Test")
public class FactoryUserTest {

    @Test
    @DisplayName("Should be created new secretary")
    public void should_be_created_new_secretary() {

        var  secretary = UserFactory.createUser(null, "Secretary", "Secretary@gmail.com", "123456", Roles.SECRETARY);

        assertEquals(secretary.getRole(), Roles.valueOf("SECRETARY"));
        assertEquals(secretary.getClass(), Secretary.class);
    }

    @Test
    @DisplayName("Should be created new teacher")
    public void should_be_created_new_teacher() {

        var  teacher = UserFactory.createUser(null,"Teacher", "teacher@gmail.com", "123456", Roles.TEACHER);

        assertEquals(teacher.getRole(), Roles.valueOf("TEACHER"));
        assertEquals(teacher.getClass(), Teacher.class);
    }

}
