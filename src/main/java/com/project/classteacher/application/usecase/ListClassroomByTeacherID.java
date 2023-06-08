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
public class ListClassroomByTeacherID {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Classroom> execute(UUID teacherId){
        var teacher = this.userRepository.findTeacherById(teacherId);
        if(teacher == null){
            // pass the teacher id to the exception example: teacher not found for id: {teacherId}
            throw new TeacherNotFoundException(
                    "teacher not found for id: " + teacherId
            );
        }
        return this.classroomRepository.listByTeacherId(teacherId);
    }

}
