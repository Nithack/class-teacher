package com.project.classteacher.application.usecase;

import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.domain.entity.Secretary;
import org.springframework.stereotype.Service;

@Service
public class SaveSecretary {
    private final UserRepository userRepository;

    public SaveSecretary(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Secretary execute(Secretary secretary) {
        return this.userRepository.saveSecretary(secretary);
    }
}
