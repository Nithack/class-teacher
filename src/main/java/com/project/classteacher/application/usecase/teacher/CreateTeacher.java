package com.project.classteacher.application.usecase;

import com.project.classteacher.application.repository.UserServiceRepository;
import com.project.classteacher.domain.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTeacher {
    @Autowired
    private UserServiceRepository userServiceRepository;

    public Teacher execute(Teacher teacher) {
        return this.userServiceRepository.save(teacher);
    }
}
