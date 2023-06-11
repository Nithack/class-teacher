package com.project.classteacher.application.usecase.classroom;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.port.ClassroomAdapter;
import com.project.classteacher.application.port.UserAdapter;
import com.project.classteacher.domain.entity.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public final class ListClassroomByTeacherID {


    private final ClassroomAdapter classroomAdapter;
    private final UserAdapter userAdapter;

    public List<Classroom> execute(UUID teacherId) {
        var teacher = this.userAdapter.findById(teacherId);
        if (teacher == null) {
            throw new TeacherNotFoundException(teacherId);
        }
        return this.classroomAdapter.listByTeacherId(teacherId);
    }

}
