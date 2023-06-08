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
        teacher.setId(generateId());
        return teacher;
    }

    @Override
    public Secretary saveSecretary(Secretary secretary) {
        secretary.setId(generateId());
        return secretary;
    }

    private static UUID generateId(){
        return UUID.randomUUID();
    }
}
