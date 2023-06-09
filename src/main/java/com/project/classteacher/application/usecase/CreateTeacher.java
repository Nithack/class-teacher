package com.project.classteacher.application.usecase;

import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.domain.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveTeacher {
    @Autowired
    private UserRepository userRepository;
    public Teacher execute(Teacher teacher) {
        return this.userRepository.saveTeacher(teacher);
    }
}
