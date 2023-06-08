package com.project.classteacher.util;

import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public class FakeUserRepository implements UserRepository {
    @Override
    public Teacher saveTeacher(Teacher teacher) {
        teacher.setId(UUID.randomUUID());
        return teacher;
    }

    @Override
    public Secretary saveSecretary(Secretary secretary) {
        secretary.setId(UUID.randomUUID());
        return secretary;
    }
}
