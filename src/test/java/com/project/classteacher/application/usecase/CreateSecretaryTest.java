package com.project.classteacher.application.usecase;

import com.project.classteacher.application.port.ClassroomPort;
import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.application.usecase.secretary.CreateSecretary;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Save Secretary Test")
@SpringBootTest(classes = CreateSecretary.class)
class CreateSecretaryTest {

    UUID DEFAULT_UUID;
    @MockBean
    private ClassroomPort classroomPort;
    @MockBean
    private UserPort userPort;
    @Autowired
    private CreateSecretary createSecretary;

    @BeforeEach
    public void setUp() {
        this.DEFAULT_UUID = TestBuilderUtil.generateId();
    }

    @Test
    @DisplayName("Should be created new secretary with information")
    void should_be_created_new_secretary_with_id() {

        var secretary = TestBuilderUtil.createSecretary(
                this.DEFAULT_UUID,
                "Secretary 1",
                "secretary1@gmail.com",
                "123456"
        );

        Mockito.when(userPort.save(secretary)).thenReturn(secretary);
        var secretarySaved = createSecretary.execute(secretary);


        assertEquals(secretarySaved.getId(), this.DEFAULT_UUID);
        assertEquals("Secretary 1", secretarySaved.getName());
        assertEquals("secretary1@gmail.com", secretarySaved.getEmail());
        assertTrue(secretarySaved.isValidPassword("123456"));
    }

    @Test
    @DisplayName("Should be created new secretary with secretary role")
    void should_be_created_new_secretary_with_secretary_role() {

        var secretary = TestBuilderUtil.generateSecretary();

        Mockito.when(userPort.save(secretary)).thenReturn(secretary);

        var secretarySaved = createSecretary.execute(secretary);
        assertEquals(secretarySaved.getRole(), Roles.valueOf("SECRETARY"));
    }

}
