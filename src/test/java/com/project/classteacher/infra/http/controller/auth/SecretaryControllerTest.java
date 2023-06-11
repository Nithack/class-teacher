package com.project.classteacher.infra.http.controller.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.classteacher.config.MyIntegrationConfig;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.infra.dataBase.mongoDB.model.UserModel;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import com.project.classteacher.util.mock.MockGenerate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    @DisplayName("Should be approved teacher")
    public void should_be_approved_teacher() throws Exception {
        List<UserModel> users = mockGenerate.generateMultiplesTeachers(10);
        List<UserModel> usersUnapproved = users.stream()
                .filter(userModel -> Objects.equals(userModel.getApproved(), "false"))
                .toList();

        UserModel secretary = mockGenerate.createUser("secretary", Roles.SECRETARY);
        Token userToken = Token.encode(secretary.toDomain());

        mockGenerate.generateMultiplesTeachers(10);
        var mvcResult = mockMvc.perform(get("/secretary/unapproved")

                        .header("Authorization", userToken.getToken())
                ).andExpect(status().isOk())
                .andReturn();
        verifyResponseContent(usersUnapproved, mvcResult.getResponse().getContentAsString());

    }

    private void verifyResponseContent(List<UserModel> expectedUsers, String responseContent) throws Exception {
        List<UserModel> actualUsers = objectMapper.readValue(responseContent, new TypeReference<List<UserModel>>() {
        });

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
