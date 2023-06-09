package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.repository.ClassroomServiceRepository;
import com.project.classteacher.application.repository.UserServiceRepository;
import com.project.classteacher.domain.entity.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateClassroom {

    @Autowired
    private ClassroomServiceRepository classroomServiceRepository;

    @Autowired
    private UserServiceRepository userServiceRepository;

    public Classroom execute(Classroom classroom) throws TeacherNotFoundException {
        var teacher = this.userServiceRepository.findById(classroom.getTeacherId());

        if (teacher == null) throw new TeacherNotFoundException(classroom.getTeacherId());

        return this.classroomServiceRepository.save(classroom);
    }
}
