package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.repository.ClassroomServiceRepository;
import com.project.classteacher.application.repository.UserServiceRepository;
import com.project.classteacher.domain.entity.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
final class ListClassroomByTeacherID {

    @Autowired
    private ClassroomServiceRepository classroomServiceRepository;

    @Autowired
    private UserServiceRepository userServiceRepository;

    public List<Classroom> execute(UUID teacherId) {
        var teacher = this.userServiceRepository.findById(teacherId);
        if (teacher == null) {
            throw new TeacherNotFoundException(teacherId);
        }
        return this.classroomServiceRepository.listByTeacherId(teacherId);
    }

}
