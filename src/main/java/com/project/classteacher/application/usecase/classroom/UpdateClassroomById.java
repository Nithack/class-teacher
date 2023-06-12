package com.project.classteacher.application.usecase.classroom;

import com.project.classteacher.application.exceptions.ClassroomNotFoundException;
import com.project.classteacher.application.exceptions.TeacherNotApprovedException;
import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.port.ClassroomPort;
import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.domain.entity.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateClassroomById {


    private final ClassroomPort classroomPort;
    private final UserPort userPort;

    public Classroom execute(UUID id, Classroom inputClassroom) {

        this.validateClassroom(id);
        this.validateTeacher(inputClassroom.getTeacherId());

        inputClassroom.setId(id);
        return this.classroomPort.save(inputClassroom);
    }

    private void validateTeacher(UUID teacherId) {
        if (teacherId != null) {
            var teacher = this.userPort.findTeacherById(teacherId);
            if (teacher == null) throw new TeacherNotFoundException(teacherId);
            if (!teacher.isApproved()) throw new TeacherNotApprovedException(teacherId);
        }
    }

    private void validateClassroom(UUID classroomId) {
        var classroom = this.classroomPort.getByID(classroomId);
        if (classroom == null) throw new ClassroomNotFoundException(classroomId);
    }
}
