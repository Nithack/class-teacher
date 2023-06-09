package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.repository.ClassroomRepository;
import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.domain.entity.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateClassroom {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private UserRepository userRepository;

    public Classroom execute(Classroom classroom) {

        var teacher = this.userRepository.findTeacherById(classroom.getTeacherId());
        if (teacher == null) {
            throw new TeacherNotFoundException(classroom.getTeacherId());
        }
        return this.classroomRepository.save(classroom);
    }
}
