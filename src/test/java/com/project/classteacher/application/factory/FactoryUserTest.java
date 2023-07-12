package com.project.classteacher.application.factory;

import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Factory User Test")
class FactoryUserTest {

    @Test
    @DisplayName("Should be created new secretary")
    void should_be_created_new_secretary() {

        var secretary = UserFactory.createUser(null, "Secretary", "Secretary@gmail.com", "123456", Roles.SECRETARY);

        assertEquals(secretary.getRole(), Roles.valueOf("SECRETARY"));
        assertEquals(secretary.getClass(), Secretary.class);
    }

    @Test
    @DisplayName("Should be created new teacher")
    void should_be_created_new_teacher() {

        var teacher = UserFactory.createUser(null, "Teacher", "teacher@gmail.com", "123456", Roles.TEACHER);

        assertEquals(teacher.getRole(), Roles.valueOf("TEACHER"));
        assertEquals(teacher.getClass(), Teacher.class);
    }


    @Test
    @DisplayName("Should be created user based on existing user")
    void should_be_created_user_based_on_existing_user() {

        var teacher = UserFactory.buildExistingUser(
                TestBuilderUtil.generateId(),
                "Teacher",
                "teacher@gmail.com",
                "123456",
                Roles.TEACHER,
                "123456",
                false
        );

        assertEquals(teacher.getRole(), Roles.valueOf("TEACHER"));
        assertEquals(teacher.getClass(), Teacher.class);

        var secretary = UserFactory.buildExistingUser(
                TestBuilderUtil.generateId(),
                "Secretary",
                "secretary@gmail.com",
                "123456",
                Roles.SECRETARY,
                "123456",
                false
        );
        assertEquals(secretary.getRole(), Roles.valueOf("SECRETARY"));
        assertEquals(secretary.getClass(), Secretary.class);

    }
}
