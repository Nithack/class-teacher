package com.project.classteacher.application.usecase;

import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.domain.entity.Secretary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSecretary {
    @Autowired
    private UserRepository userRepository;
    public Secretary execute(Secretary secretary) {
        return this.userRepository.saveSecretary(secretary);
    }
}
