package com.project.classteacher.domain;

import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@DisplayName("Auth Token Test")
@SpringBootTest
@ActiveProfiles("test")
public class AuthTokenTest {

    private UUID DEFAULT_UUID;

    @BeforeEach
    public void setUp() {
        DEFAULT_UUID = TestBuilderUtil.generateId();
    }

    @Test()
    @DisplayName("should be return Token decoded")
    public void should_be_return_token_decoded() {


        User decodedToken = TestBuilderUtil.generateUser();

        Token token = TestBuilderUtil.createToken(
                decodedToken
        );

        User authToken = Token.decode(token.getToken());

        assertAll(
                () -> assertEquals("Name validated", authToken.getName(), decodedToken.getName()),
                () -> assertEquals("Id validated", authToken.getId(), decodedToken.getId()),
                () -> assertEquals("Email validated", authToken.getEmail(), decodedToken.getEmail())
        );
    }

    @Test()
    @DisplayName("should be return AuthToken encoded")
    public void should_be_return_token_encoded() {

        User user = TestBuilderUtil.generateUser();

        Token token = Token.encode(user);

        Assertions.assertEquals("token", token.getToken());
    }

}
