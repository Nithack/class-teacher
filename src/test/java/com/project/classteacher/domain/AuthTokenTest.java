package com.project.classteacher.domain;

import com.project.classteacher.application.exceptions.InvalidTokenException;
import com.project.classteacher.domain.entity.DecodeToken;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@DisplayName("Auth Token Test")
public class AuthTokenTest {
    @Test()
    @DisplayName("should be return Token decoded")
    public void should_be_return_token_decoded() {


        User decodedToken = TestBuilderUtil.generateUser();

        Token token = TestBuilderUtil.createToken(
                decodedToken
        );

        DecodeToken authToken = Token.decode(token.getToken());

        assertAll(
                () -> assertEquals("Name validated", authToken.getName(), decodedToken.getName()),
                () -> assertEquals("Id validated", authToken.getId(), decodedToken.getId()),
                () -> assertEquals("Email validated", authToken.getEmail(), decodedToken.getEmail()),
                () -> assertEquals("Role validated", authToken.getRole(), decodedToken.getRole())
        );
    }

    @Test()
    @DisplayName("should be return AuthToken encoded")
    public void should_be_return_token_encoded() {

        User user = TestBuilderUtil.generateUser();

        Token token = Token.encode(user);

        DecodeToken authToken = Token.decode(token.getToken());

        assertAll(
                () -> assertEquals("Name validated", authToken.getName(), user.getName()),
                () -> assertEquals("Id validated", authToken.getId(), user.getId()),
                () -> assertEquals("Email validated", authToken.getEmail(), user.getEmail()),
                () -> assertEquals("Role validated", authToken.getRole(), user.getRole())
        );
    }

    @Test
    @DisplayName("should be return exception when token is expired")
    public void should_be_return_exception_when_token_is_expired() {
        User decodedToken = TestBuilderUtil.generateUser();


        Token expiredToken = Token.builder()
                .token(TestBuilderUtil.createExpiredToken(decodedToken))
                .build();


        assertThrows(InvalidTokenException.class, () -> {
            DecodeToken authToken = Token.decode(expiredToken.getToken());
        });
    }

    @Test
    @DisplayName("should be return exception when token is invalid")
    public void should_be_return_exception_when_token_is_invalid() {
        assertThrows(InvalidTokenException.class, () -> {
            DecodeToken authToken = Token.decode("invalid token");
        });

    }

}
