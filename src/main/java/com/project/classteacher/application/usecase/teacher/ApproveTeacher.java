package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.repository.UserServiceRepository;
import com.project.classteacher.domain.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApproveTeacher {
    @Autowired
    private UserServiceRepository userServiceRepository;

    public Teacher execute(UUID teacherId) {

        var teacher = userServiceRepository.findById(teacherId);

        if (teacher == null) throw new TeacherNotFoundException(teacherId);

        teacher.approve();

        return userServiceRepository.save(teacher);
    }
}
