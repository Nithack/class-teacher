package com.project.classteacher.application.usecase.teacher;

import com.project.classteacher.application.port.UserAdapter;
import com.project.classteacher.domain.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTeacher {

    private final UserAdapter userAdapter;

    public Teacher execute(Teacher teacher) {
        return this.userAdapter.save(teacher);
    }
}
