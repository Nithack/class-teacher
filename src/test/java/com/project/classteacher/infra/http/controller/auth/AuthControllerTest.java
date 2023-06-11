package com.project.classteacher.infra.http.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.classteacher.config.MyIntegrationConfig;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.infra.dataBase.mongoDB.model.UserModel;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import com.project.classteacher.infra.http.dtos.LoginDTO;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthControllerTest extends MyIntegrationConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserMongoDBRepository userMongoDBRepository;

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
    @DisplayName("Should return 200 when providing valid email and password")
    void should_return_200_when_providing_valid_email_and_password() throws Exception {


        User user = TestBuilderUtil.generateUser();
        LoginDTO loginDTO = TestBuilderUtil.createLoginDTO(user);
        Token token = TestBuilderUtil.createToken(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(loginDTO);


        Mockito.when(userMongoDBRepository.findOne(Example.of(
                UserModel.builder()
                        .email(user.getEmail())
                        .build()
        ))).thenReturn(
                TestBuilderUtil.createOptionalUserModel(user)
        );

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpectAll(
                        status().is(200),
                        MockMvcResultMatchers.jsonPath("$.token").value(token.getToken())
                );
    }

}
