package com.project.classteacher.infra.http.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.classteacher.config.MyIntegrationConfig;
import com.project.classteacher.domain.entity.DecodeToken;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.infra.dataBase.mongoDB.model.UserModel;
import com.project.classteacher.infra.http.dtos.CreateTeacherDTO;
import com.project.classteacher.infra.http.dtos.LoginDTO;
import com.project.classteacher.util.builder.TestBuilderUtil;
import com.project.classteacher.util.mock.MockGenerate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthControllerTest extends MyIntegrationConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockGenerate mockGenerate;


    @Test
    @DisplayName("Should return 400 when missing user and password")
    public void should_return_status_code_200_when_login() throws Exception {

        mockMvc.perform(post("/auth/login")

                        .header("Authorization", "")
                )
                .andExpectAll(
                        MockMvcResultMatchers.jsonPath("$.status").value(400)
                );
    }

    @Test
    @DisplayName("Should return 401 when access with invalid token")
    void should_return_401_when_access_with_invalid_token() throws Exception {

        mockMvc.perform(post("/auth/login")
                        .header("Authorization", "Potato bread")
                )
                .andExpect(status().isUnauthorized())
                .andExpectAll(
                        MockMvcResultMatchers.jsonPath("$.status").value(401),
                        MockMvcResultMatchers.jsonPath("$.message").value("User not authorized")
                );
    }

    @Test
    @DisplayName("Should return 200 when providing valid email and password to login")
    void should_return_200_when_providing_valid_email_and_password_to_login() throws Exception {

        String REFERENCE = "REFERENCE";

        UserModel user = mockGenerate.createUser(REFERENCE, Roles.TEACHER, false);

        LoginDTO loginDTO = TestBuilderUtil.createLoginDTO(user.getEmail(), "password" + REFERENCE);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(loginDTO);

        var result = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn()
                .getResponse()
                .getContentAsString();
        String tokenResponse = objectMapper.readTree(result).get("token").asText();

        DecodeToken resultToken = Token.decode(tokenResponse);
        assertAll(
                () -> assertEquals(user.getEmail(), resultToken.getEmail()),
                () -> assertEquals(Roles.valueOf(user.getRole()), resultToken.getRole()),
                () -> assertEquals(user.getId(), resultToken.getId()),
                () -> assertEquals(user.getName(), resultToken.getName())
        );
    }

    @Test
    @DisplayName("Should register new user and return 200")
    void should_register_new_user_and_return_200() throws Exception {

        User user = TestBuilderUtil.generateUser();

        CreateTeacherDTO DTO = TestBuilderUtil.createTeacherDTO(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(DTO);

        mockMvc.perform(
                        post("/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpectAll(
                        status().is(200),
                        MockMvcResultMatchers.jsonPath("$.name").value(user.getName()),
                        MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()),
                        MockMvcResultMatchers.jsonPath("$.role").value(user.getRole().toString()),
                        MockMvcResultMatchers.jsonPath("$.token").exists(),
                        MockMvcResultMatchers.jsonPath("$.id").exists(),
                        MockMvcResultMatchers.jsonPath("$.approved").value(false)
                );

    }

    @Test
    @DisplayName("Should exception because user already exists")
    void should_exception_because_user_already_exists() throws Exception {

        UserModel user = mockGenerate.createUser("teacher", Roles.TEACHER, true);
        CreateTeacherDTO teacherTDT = TestBuilderUtil.createTeacherDTO(user.toDomain());

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(teacherTDT);


        mockMvc.perform(
                        post("/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpectAll(
                        MockMvcResultMatchers.jsonPath("$.status").value(409),
                        MockMvcResultMatchers.jsonPath("$.message").value("User exist in database: " + user.getEmail())
                );

    }
}
