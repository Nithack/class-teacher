package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.repository.ClassroomRepository;
import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.domain.entity.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
final class ListClassroomByTeacherID {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Classroom> execute(UUID teacherId){
        var teacher = this.userRepository.findTeacherById(teacherId);
        if(teacher == null){
            throw new TeacherNotFoundException(teacherId);
        }
        return this.classroomRepository.listByTeacherId(teacherId);
    }

}
