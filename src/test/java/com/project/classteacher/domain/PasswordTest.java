package com.project.classteacher.domain;

import com.project.classteacher.domain.entity.Password;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.*;

@DisplayName("Password Test")
public class PasswordTest {

    private static final String PASSWORD = "myPassword";
    private static final String SALT = "mySalt";

    @Test
    @DisplayName("Should create and validate password successfully")
    public void shouldCreateAndValidatePassword() {
        Password password = Password.create(PASSWORD, SALT);

        var passwordByExistHash = new Password(password.getValue(), password.getSalt());

        assertNotNull(password);
        assertEquals("Password salt should be defined",SALT, password.getSalt());
        assertTrue("Password should be valid", passwordByExistHash.validate(PASSWORD));
    }

    @Test
    @DisplayName("Should validate password and return false for invalid password")
    public void shouldValidatePasswordAndReturnFalseForInvalidPassword() {
        Password password = Password.create(PASSWORD, SALT);

        var passwordByExistHash = new Password(password.getValue(), password.getSalt());

        assertFalse("Password should be invalid by create Password",password.validate("wrongPassword"));
        assertFalse("Password should be invalid by new Password",passwordByExistHash.validate("wrongPassword"));
    }
}