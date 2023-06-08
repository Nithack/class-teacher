package com.project.classteacher.application.factory;

import com.project.classteacher.ConfigContainersTest;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.application.factory.UserFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@ConfigContainersTest
public class FactoryUserTest {

    @Test
    public void should_be_created_new_secretary() {

        var  teacher = UserFactory.createUser("Secretary", "Secretary@gmail.com", "123456", Roles.SECRETARY);

        assertEquals(teacher.getRole(), Roles.valueOf("SECRETARY"));
    }

    @Test
    public void should_be_created_new_teacher() {

        var  teacher = UserFactory.createUser("Teacher", "teacher@gmail.com", "123456", Roles.TEACHER);

        assertEquals(teacher.getRole(), Roles.valueOf("TEACHER"));
        assertEquals(teacher.getClass(), Secretary.class);
    }

}
