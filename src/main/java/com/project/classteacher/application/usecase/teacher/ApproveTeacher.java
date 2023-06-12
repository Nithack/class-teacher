package com.project.classteacher.application.usecase.teacher;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.domain.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApproveTeacher {


    private final UserPort userPort;

    public Teacher execute(UUID teacherId) {

        var teacher = (Teacher) userPort.findTeacherById(teacherId);

        if (teacher == null) throw new TeacherNotFoundException(teacherId);

        teacher.approve();
        userPort.save(teacher);

        return teacher;

    }
}
