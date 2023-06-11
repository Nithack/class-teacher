package com.project.classteacher.application.usecase.classroom;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.port.ClassroomPort;
import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.domain.entity.Classroom;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateClassroom {

    private final ClassroomPort classroomPort;
    private final UserPort userPort;

    public Classroom execute(@NotNull Classroom classroom) throws TeacherNotFoundException {
        var teacher = this.userPort.findById(classroom.getTeacherId());

        if (teacher == null) throw new TeacherNotFoundException(classroom.getTeacherId());

        return this.classroomPort.save(classroom);
    }
}
