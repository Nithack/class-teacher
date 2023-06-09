package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.UserNotFoundException;
import com.project.classteacher.application.factory.UserFactory;
import com.project.classteacher.application.repository.UserServiceRepository;
import com.project.classteacher.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserByEmail {

    @Autowired
    private UserServiceRepository userServiceRepository;

    public User execute(String email) {
        User user = this.userServiceRepository.getByEmail(email);
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
