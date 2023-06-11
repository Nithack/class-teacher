package com.project.classteacher.application.usecase.teacher;

import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.domain.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTeacher {

    private final UserPort userPort;

    public Teacher execute(Teacher teacher) {
        return this.userPort.save(teacher);
    }
}
