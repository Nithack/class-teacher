package com.project.classteacher.application.usecase;

import com.project.classteacher.ConfigContainersTest;
import com.project.classteacher.domain.entity.Roles;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.util.FakeUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@ConfigContainersTest
public class SaveSecretaryTest {

    private Secretary teacher;

    private SaveSecretary saveSecretary = new SaveSecretary(new FakeUserRepository());

    @Test
    public void should_be_created_new_teacher_with_id() {

        var newSecretary = Secretary.builder()
                .id(UUID.randomUUID())
                .name("Teacher 1")
                .password("123456")
                .email("secretary@gmail.com")
                .build();

        var teacherSaved = saveSecretary.execute(newSecretary);
        assertThat(teacherSaved.getId(), notNullValue());
    }

    @Test
    public void should_be_created_new_teacher_with_teacher_role() {

        var newSecretary = Secretary.builder()
                .id(UUID.randomUUID())
                .name("Teacher 1")
                .password("123456")
                .email("secretary@gmail.com")
                .build();

        var teacherSaved = saveSecretary.execute(newSecretary);
        assertEquals(teacherSaved.getRole(), Roles.valueOf("SECRETARY"));
    }

}
