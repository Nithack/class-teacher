package com.project.classteacher.application.usecase.login;

import com.project.classteacher.application.exceptions.InvalidPasswordException;
import com.project.classteacher.application.exceptions.TeacherNotApprovedException;
import com.project.classteacher.application.exceptions.UserNotFoundException;
import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUser {

    private final UserPort userPort;

    public Token execute(String email, String password) {
        User userData = this.userPort.getByEmail(email);

        validateUser(userData, email, password);

        return Token.encode(userData);
    }

    private void validateUser(User user, String email, String password) {
        if (user == null) throw new UserNotFoundException(email);
        if (Boolean.FALSE.equals(user.isValidPassword(password))) throw new InvalidPasswordException(password);
        if (user.getRole() == Roles.TEACHER && Boolean.TRUE.equals(!user.isApproved())) throw new TeacherNotApprovedException(user.getId());
    }
}
