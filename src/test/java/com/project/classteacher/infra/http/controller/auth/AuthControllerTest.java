package com.project.classteacher.infra.http.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.classteacher.config.MyIntegrationConfig;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.infra.dataBase.mongoDB.model.UserModel;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import com.project.classteacher.infra.http.dtos.CreateTeacherDTO;
import com.project.classteacher.infra.http.dtos.LoginDTO;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthControllerTest extends MyIntegrationConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserMongoDBRepository userMongoDBRepository;

    @BeforeEach
    void setUp() {
        Mockito.reset(userMongoDBRepository);
    }

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


        String password = "123456";

        User user = TestBuilderUtil.createUser(
                TestBuilderUtil.generateId(),
                "test",
                "test@gmail.com",
                password,
                Roles.TEACHER,
                false
        );
        LoginDTO loginDTO = TestBuilderUtil.createLoginDTO(user.getName(), password);
        Token token = TestBuilderUtil.createToken(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(loginDTO);


        Mockito.when(userMongoDBRepository.findOne(
                ArgumentMatchers.any(Example.class)
        )).thenReturn(
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

    @Test
    @DisplayName("Should register new user and return 200")
    void should_register_new_user_and_return_200() throws Exception {

        User user = TestBuilderUtil.generateUser();
        CreateTeacherDTO teacherTDT = TestBuilderUtil.createTeacherDTO(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(teacherTDT);

        Optional<UserModel> emptyOptional = Optional.empty();
        Mockito.when(userMongoDBRepository.findOne(ArgumentMatchers.any()))
                .thenReturn(emptyOptional);

        Mockito.when(userMongoDBRepository.save(Mockito.any(UserModel.class))).thenReturn(
                TestBuilderUtil.createUserModel(user)
        );

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

        User user = TestBuilderUtil.generateUser();
        CreateTeacherDTO teacherTDT = TestBuilderUtil.createTeacherDTO(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(teacherTDT);

        Mockito.when(userMongoDBRepository.findOne(ArgumentMatchers.any()))
                .thenReturn(
                        TestBuilderUtil.createOptionalUserModel(user)
                );

        Mockito.when(userMongoDBRepository.save(Mockito.any(UserModel.class))).thenReturn(
                TestBuilderUtil.createUserModel(user)
        );

        mockMvc.perform(
                        post("/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpectAll(
                        MockMvcResultMatchers.jsonPath("$.status").value(409),
                        MockMvcResultMatchers.jsonPath("$.message").value("User exist in database: test@gmail.com")
                );

    }
}
