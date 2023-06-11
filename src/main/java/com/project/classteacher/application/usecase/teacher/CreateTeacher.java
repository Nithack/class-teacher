package com.project.classteacher.application.usecase.teacher;

import com.project.classteacher.application.exceptions.ExistsUserException;
import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTeacher {

    private final UserPort userPort;

    public Teacher execute(Teacher teacher) {
        User user = this.userPort.getByEmail(teacher.getEmail());
        if (user != null) {
            throw new ExistsUserException(teacher.getEmail());
        }
        return this.userPort.save(teacher);
    }
}
