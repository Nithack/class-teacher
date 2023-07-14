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
        var classroom = this.classroomPort.getByID(id);

        this.validateClassroom(id, classroom);
        this.validateTeacher(inputClassroom.getTeacherId());

        var updatedClassroom = this.updateClassroom(inputClassroom, classroom);

        return this.classroomPort.save(updatedClassroom);
    }

    private void validateTeacher(UUID teacherId) {
        if (teacherId != null) {
            var teacher = this.userPort.findTeacherById(teacherId);
            if (teacher == null) throw new TeacherNotFoundException(teacherId);
            if (Boolean.FALSE.equals(teacher.isApproved())) throw new TeacherNotApprovedException(teacherId);
        }
    }

    private void validateClassroom(UUID id, Classroom classroom) {
        if (classroom == null) throw new ClassroomNotFoundException(id);
    }

    private Classroom updateClassroom(Classroom inputClassroom, Classroom classroom) {
        if (inputClassroom.getTitle() != null) classroom.setTitle(inputClassroom.getTitle());
        if (inputClassroom.getTeacherId() != null) classroom.setTeacherId(inputClassroom.getTeacherId());
        if (inputClassroom.getDescription() != null) classroom.setDescription(inputClassroom.getDescription());
        if (inputClassroom.getDayDate() != null) classroom.setDayDate(inputClassroom.getDayDate());
        return classroom;
    }
}
