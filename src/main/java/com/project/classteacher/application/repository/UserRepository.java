package com.project.classteacher.application.repository;

import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import java.util.UUID;

public interface UserRepository {
    Teacher saveTeacher(Teacher teacher);
    Secretary saveSecretary(Secretary secretary);
    Teacher findTeacherById(UUID id);
}
