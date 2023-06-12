package com.project.classteacher.infra.http.controller.secretary;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.classteacher.config.MyIntegrationConfig;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.infra.dataBase.mongoDB.model.ClassroomModel;
import com.project.classteacher.infra.dataBase.mongoDB.model.UserModel;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import com.project.classteacher.infra.http.dtos.ClassroomOutputDTO;
import com.project.classteacher.infra.http.dtos.ClassroomUpdateDTO;
import com.project.classteacher.infra.http.dtos.CreateClassroomDTO;
import com.project.classteacher.util.builder.TestBuilderUtil;
import com.project.classteacher.util.mock.MockGenerate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class SecretaryControllerTest extends MyIntegrationConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserMongoDBRepository userMongoDBRepository;

    @Autowired
    private MockGenerate mockGenerate;

    @BeforeEach
    public void setUp() {
        userMongoDBRepository.deleteAll();
    }

    @Test()
    @DisplayName("Should be return 403 when not have token")
    public void should_be_return_401_when_not_have_token() throws Exception {

        mockMvc.perform(get("/secretary/unapproved"))
                .andExpect(status().is4xxClientError());

        mockMvc.perform(post("/secretary/approve/" + TestBuilderUtil.generateId())
        ).andExpect(status().is4xxClientError());

        mockMvc.perform(post("/secretary/classroom")
        ).andExpect(status().is4xxClientError());

        mockMvc.perform(put("/secretary/classroom/" + TestBuilderUtil.generateId())
        ).andExpect(status().is4xxClientError());

    }

    @Test
    @DisplayName("Should be list unapproved teacher")
    public void should_be_list_unapproved_teacher() throws Exception {

        var QUANTITY = 10;
        List<UserModel> users = mockGenerate.generateMultiplesTeachers(QUANTITY);

        List<UserModel> usersUnapproved = users.stream()
                .filter(userModel -> Objects.equals(userModel.getApproved(), false))
                .toList();

        UserModel secretary = mockGenerate.createUser("secretary", Roles.SECRETARY, true);
        Token userToken = Token.encode(secretary.toDomain());

        var mvcResult = mockMvc.perform(get("/secretary/unapproved")

                        .header("Authorization", userToken.getToken())
                ).andExpect(status().isOk())
                .andReturn();

        verifyResponseContent(usersUnapproved, mvcResult.getResponse().getContentAsString(), usersUnapproved.size());
    }

    @Test
    @DisplayName("Should be approved teacher")
    public void should_be_approved_teacher() throws Exception {

        UserModel teacher = mockGenerate.createUser("teacher", Roles.TEACHER, false);

        UserModel secretary = mockGenerate.createUser("secretary", Roles.SECRETARY, true);

        Token secretaryToken = Token.encode(secretary.toDomain());

        mockMvc.perform(post("/secretary/approve/" + teacher.getId())

                        .header("Authorization", secretaryToken.getToken())
                ).andExpect(status().isOk())
                .andReturn();

        var teacherApproved = userMongoDBRepository.findById(teacher.getId());
        assertEquals(true, teacherApproved.orElseThrow().getApproved());
    }

    @DisplayName("Should be create new classroom")
    @Test
    public void should_be_create_new_classroom() throws Exception {

        UserModel teacher = mockGenerate.createUser("teacher", Roles.TEACHER, true);

        UserModel secretary = mockGenerate.createUser("secretary", Roles.SECRETARY, true);

        Token secretaryToken = Token.encode(secretary.toDomain());

        CreateClassroomDTO classroomDTO = TestBuilderUtil
                .createClassroomDTO(
                        UUID.randomUUID(),
                        "Literature",
                        "This is a literature class",
                        new Date(),
                        teacher.getId()
                );

        String requestBody = objectMapper.writeValueAsString(classroomDTO);

        var responseContent = mockMvc.perform(post("/secretary/classroom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .header("Authorization", secretaryToken.getToken())
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        ClassroomOutputDTO updatedClassroom = objectMapper.readValue(responseContent, new TypeReference<>() {
        });

        assertAll(
                () -> assertEquals(classroomDTO.getTitle(), updatedClassroom.getTitle()),
                () -> assertEquals(classroomDTO.getDescription(), updatedClassroom.getDescription()),
                () -> assertEquals(classroomDTO.getTeacherId(), updatedClassroom.getTeacherId())
        );

    }

    @DisplayName("Should be update classroom")
    @Test
    public void should_be_update_classroom() throws Exception {

        UserModel teacher = mockGenerate.createUser("teacher", Roles.TEACHER, true);

        ClassroomModel classroom = mockGenerate.createClassroom(teacher.getId());

        Token teacherToken = Token.encode(teacher.toDomain());

        ClassroomUpdateDTO classroomDTO = TestBuilderUtil
                .createClassroomUpdateDTO(
                        "Designer of Software",
                        "This is a Designer of Software class",
                        TestBuilderUtil.generateId(),
                        null
                );

        String requestBodyUpdate = objectMapper.writeValueAsString(classroomDTO);

        assert classroomDTO.getTeacherId() != null;
        var responseContent = mockMvc.perform(put("/secretary/classroom/" + classroom.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyUpdate)
                        .header("Authorization", teacherToken.getToken())
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ClassroomOutputDTO updatedClassroom = objectMapper.readValue(responseContent, new TypeReference<>() {
        });

        assertAll("Verify updated classroom",
                () -> assertEquals(classroomDTO.getTitle(), updatedClassroom.getTitle()),
                () -> assertEquals(classroomDTO.getDescription(), updatedClassroom.getDescription()),
                () -> assertEquals(classroomDTO.getTeacherId().toString(), updatedClassroom.getTeacherId().toString())
        );

    }

    private void verifyResponseContent(List<UserModel> expectedUsers, String responseContent, Number quantity) throws Exception {
        List<UserModel> actualUsers = objectMapper.readValue(responseContent, new TypeReference<>() {
        });
        assertEquals(quantity.intValue(), actualUsers.size());
        for (UserModel expectedUser : expectedUsers) {
            assertAll(
                    () -> assertTrue(actualUsers.stream().anyMatch(actualUser -> actualUser.getId().equals(expectedUser.getId()))),
                    () -> assertTrue(actualUsers.stream().anyMatch(actualUser -> actualUser.getName().equals(expectedUser.getName()))),
                    () -> assertTrue(actualUsers.stream().anyMatch(actualUser -> actualUser.getEmail().equals(expectedUser.getEmail()))),
                    () -> assertTrue(actualUsers.stream().anyMatch(actualUser -> actualUser.getRole().equals(expectedUser.getRole()))),
                    () -> assertTrue(actualUsers.stream().anyMatch(actualUser -> actualUser.getApproved().equals(expectedUser.getApproved())))
            );
        }
    }

}
