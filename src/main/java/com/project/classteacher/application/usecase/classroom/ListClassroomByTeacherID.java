package com.project.classteacher.application.usecase.classroom;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.port.ClassroomPort;
import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.domain.entity.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public final class ListClassroomByTeacherID {


    private final ClassroomPort classroomPort;
    private final UserPort userPort;

    public List<Classroom> execute(UUID teacherId) {
        var teacher = this.userPort.findById(teacherId);
        if (teacher == null) {
            throw new TeacherNotFoundException(teacherId);
        }
        return this.classroomPort.listByTeacherId(teacherId);
    }

}
