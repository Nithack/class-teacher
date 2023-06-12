package com.project.classteacher.application.usecase.login;

import com.project.classteacher.application.exceptions.InvalidPasswordException;
import com.project.classteacher.application.exceptions.TeacherNotApprovedException;
import com.project.classteacher.application.usecase.user.GetUserByEmail;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUser {

    private final GetUserByEmail getUserByEmail;

    public Token execute(String email, String password) {
        User user = getUserByEmail.execute(email);
        if (!user.isValidPassword(password)) throw new InvalidPasswordException(password);
        if (user.getRole() == Roles.TEACHER && !user.isApproved()) throw new TeacherNotApprovedException(user.getId());
        return Token.encode(user);
    }
}
