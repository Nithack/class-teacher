package com.project.classteacher.domain.entity;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Password {
    private static final int STRENGTH = 12;
    private String value;
    private String salt;

    public static Password create(String password, String salt) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom(salt.getBytes()));
        String hashedPassword = passwordEncoder.encode(password);
        return new Password(hashedPassword, salt);
    }

    public boolean validate(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom(salt.getBytes()));
        return passwordEncoder.matches(password, value);
    }

    public String getValue() {
        return value;
    }

    public String getSalt() {
        return salt;
    }
}