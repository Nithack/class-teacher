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
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.UUID;

@DisplayName("Save Secretary Test")
@ConfigContainersTest
public class CreateSecretaryTest {

    @MockBean
    private ClassroomRepository classroomRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CreateSecretary createSecretary;

    UUID DEFAULT_UUID;

    @BeforeEach
    public void setUp() {
        this.DEFAULT_UUID = TestBuilderUtil.generateId();
    }
    @Test
    @DisplayName("Should be created new secretary with information")
    public void should_be_created_new_secretary_with_id() {

        var secretary = TestBuilderUtil.generateSecretary(this.DEFAULT_UUID,"Secretary 1", "secretary1@gmail.com", "123456");

        Mockito.when(userRepository.saveSecretary(secretary)).thenReturn(secretary);
        var secretarySaved = createSecretary.execute(secretary);


        assertEquals(secretarySaved.getId(), this.DEFAULT_UUID);
        assertEquals(secretarySaved.getName(), "Secretary 1");
        assertEquals(secretarySaved.getEmail(), "secretary1@gmail.com");
        assertEquals(secretarySaved.getPassword(), "123456");
    }

    @Test
    @DisplayName("Should be created new secretary with secretary role")
    public void should_be_created_new_secretary_with_secretary_role() {

        var secretary = TestBuilderUtil.generateSecretary(
                this.DEFAULT_UUID,
                "Secretary 1",
                "secretary1@gmail.com",
                "123456"
        );
        Mockito.when(userRepository.saveSecretary(secretary)).thenReturn(secretary);
        var secretarySaved = createSecretary.execute(secretary);
        assertEquals(secretarySaved.getRole(), Roles.valueOf("SECRETARY"));
    }

}
