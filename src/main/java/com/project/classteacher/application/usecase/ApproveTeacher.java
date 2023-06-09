package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.domain.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApproveTeacher {
    @Autowired
    private UserRepository userRepository;

    public Teacher execute(UUID teacherId) {

        var teacher = this.userRepository.findTeacherById(teacherId);

        if (teacher == null) {
            throw new TeacherNotFoundException(teacherId);
        }

        teacher.approve();

        return this.userRepository.saveTeacher(teacher);
    }
}
