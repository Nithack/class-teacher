package com.project.classteacher.application.usecase.login;

import com.project.classteacher.application.usecase.user.GetUserByEmail;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUser {

    private final GetUserByEmail getUserByEmail;

    public Token execute(String email, String password) {
        User user = getUserByEmail.execute(email);
        user.verifyPassword(password);
        return Token.encode(user);
    }
}
