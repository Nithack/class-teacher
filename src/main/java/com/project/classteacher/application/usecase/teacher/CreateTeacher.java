package com.project.classteacher.application.usecase.teacher;

import com.project.classteacher.application.repository.UserServiceRepository;
import com.project.classteacher.domain.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTeacher {

    private final UserServiceRepository userServiceRepository;

    public Teacher execute(Teacher teacher) {
        return this.userServiceRepository.save(teacher);
    }
}
