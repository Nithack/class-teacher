package com.project.classteacher.application.usecase.user;

import com.project.classteacher.application.exceptions.UserNotFoundException;
import com.project.classteacher.application.factory.UserFactory;
import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByEmail {


    private final UserPort userPort;

    public User execute(String email) {
        User user = this.userPort.getByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(email);
        }
        UserFactory.createUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
        return user;
    }
}
