package com.project.classteacher.application.repository;

import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;

public interface UserRepository {
    Teacher saveTeacher(Teacher teacher);
    Secretary saveSecretary(Secretary secretary);
}
