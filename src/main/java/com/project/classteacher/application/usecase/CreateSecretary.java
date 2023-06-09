package com.project.classteacher.application.usecase;

import com.project.classteacher.application.repository.UserServiceRepository;
import com.project.classteacher.domain.entity.Secretary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSecretary {
    @Autowired
    private UserServiceRepository userServiceRepository;

    public Secretary execute(Secretary secretary) {
        return this.userServiceRepository.save(secretary);
    }
}
