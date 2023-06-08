package com.project.classteacher.application.repository;

import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository {
    Teacher saveTeacher(Teacher teacher);
    Secretary saveSecretary(Secretary secretary);
}
