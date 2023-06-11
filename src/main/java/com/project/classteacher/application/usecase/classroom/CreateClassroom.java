package com.project.classteacher.application.usecase.classroom;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.port.ClassroomAdapter;
import com.project.classteacher.application.port.UserAdapter;
import com.project.classteacher.domain.entity.Classroom;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateClassroom {

    private final ClassroomAdapter classroomAdapter;
    private final UserAdapter userAdapter;

    public Classroom execute(@NotNull Classroom classroom) throws TeacherNotFoundException {
        var teacher = this.userAdapter.findById(classroom.getTeacherId());

        if (teacher == null) throw new TeacherNotFoundException(classroom.getTeacherId());

        return this.classroomAdapter.save(classroom);
    }
}
