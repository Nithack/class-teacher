package com.project.classteacher.application.usecase;

import com.project.classteacher.ConfigContainersTest;
import com.project.classteacher.domain.entity.Roles;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.util.FakeUserRepository;
import com.project.classteacher.util.factory.TestBuilderUtil;
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

    private SaveSecretary saveSecretary = new SaveSecretary(new FakeUserRepository());

    @Test
    public void should_be_created_new_secretary_with_id() {

        var secretary = TestBuilderUtil.generateSecretary("Secretary 1", "secretary1@gmail.com", "123456");

        var secretarySaved = saveSecretary.execute(secretary);
        assertThat(secretarySaved.getId(), notNullValue());
    }

    @Test
    public void should_be_created_new_secretary_with_secretary_role() {

        var secretary = TestBuilderUtil.generateSecretary("Secretary 1", "secretary1@gmail.com", "123456");

        var secretarySaved = saveSecretary.execute(secretary);
        assertEquals(secretarySaved.getRole(), Roles.valueOf("SECRETARY"));
    }

}
