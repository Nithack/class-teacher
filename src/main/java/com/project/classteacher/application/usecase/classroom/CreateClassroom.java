package com.project.classteacher.application.usecase.classroom;

import com.project.classteacher.application.exceptions.TeacherNotApprovedException;
import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.port.ClassroomPort;
import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.domain.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateClassroom {

    private final ClassroomPort classroomPort;
    private final UserPort userPort;

    public Classroom execute(Classroom classroom) {
        Teacher teacher = this.userPort.findTeacherById(classroom.getTeacherId());

        if (teacher == null) throw new TeacherNotFoundException(classroom.getTeacherId());
        if (Boolean.FALSE.equals(teacher.isApproved())) throw new TeacherNotApprovedException(classroom.getTeacherId());

        UUID newClassroomId = UUID.randomUUID();
        classroom.setId(newClassroomId);
        return this.classroomPort.save(classroom);
        
    }
}
