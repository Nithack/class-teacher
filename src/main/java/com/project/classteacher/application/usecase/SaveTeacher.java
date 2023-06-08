package com.project.classteacher.application.usecase;

import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.domain.entity.Teacher;
import org.springframework.stereotype.Service;

@Service
public class SaveTeacher {
    private final UserRepository userRepository;

    public SaveTeacher(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Teacher execute(Teacher teacher) {
        return this.userRepository.saveTeacher(teacher);
    }
}
