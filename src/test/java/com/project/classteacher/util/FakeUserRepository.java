package com.project.classteacher.util;

import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
@Profile("test")
public class FakeUserRepository implements UserRepository {
    @Override
    public Teacher saveTeacher(Teacher teacher) {
        if(teacher.getId() == null){
            teacher.setId(UUID.randomUUID());
        }
        return teacher;
    }

    @Override
    public Secretary saveSecretary(Secretary secretary) {
        if(secretary.getId() == null){
            secretary.setId(UUID.randomUUID());
        }
        return secretary;
    }
}
