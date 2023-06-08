package com.project.classteacher.application.usecase;

import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.config.decorators.ConfigContainersTest;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ConfigContainersTest
public class SaveSecretaryTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private SaveSecretary saveSecretary;

    @Test
    public void should_be_created_new_secretary_with_id() {

        var secretary = TestBuilderUtil.generateSecretary("Secretary 1", "secretary1@gmail.com", "123456");

        Mockito.when(userRepository.saveSecretary(secretary)).thenReturn(secretary);
        var secretarySaved = saveSecretary.execute(secretary);
        assertThat(secretarySaved.getId(), notNullValue());
    }

    @Test
    public void should_be_created_new_secretary_with_secretary_role() {

        var secretary = TestBuilderUtil.generateSecretary("Secretary 1", "secretary1@gmail.com", "123456");
        Mockito.when(userRepository.saveSecretary(secretary)).thenReturn(secretary);
        var secretarySaved = saveSecretary.execute(secretary);
        assertEquals(secretarySaved.getRole(), Roles.valueOf("SECRETARY"));
    }

}
