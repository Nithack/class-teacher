package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.factory.UserFactory;
import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class ListUnapprovedTeachers {
    @Autowired
    private UserRepository userRepository;

    public List<Teacher> execute() {

        List<User> unapprovedTeachers = userRepository.listByApprovedAndRole(false, Roles.TEACHER);

        if(unapprovedTeachers.isEmpty()) throw new TeacherNotFoundException("No unapproved teachers found");

        List<Teacher> output = new ArrayList<>();
        unapprovedTeachers.forEach(teacher -> {
            output.add(
                    (Teacher) UserFactory.buildExistingUser(
                    teacher.getId(),
                    teacher.getName(),
                    teacher.getEmail(),
                    teacher.getPassword(),
                    teacher.getRole(),
                    teacher.getSalt()
            ));

        });
        return output;
    }

}