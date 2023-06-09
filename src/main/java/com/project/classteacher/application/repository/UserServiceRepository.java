package com.project.classteacher.application.repository;

import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;

import java.util.List;
import java.util.UUID;


public interface UserServiceRepository {
    Teacher save(Teacher teacher);

    Teacher findById(UUID teacherId);

    Secretary save(Secretary secretary);

    User getByEmail(String email);

    List<User> listByApprovedAndRole(Boolean approved, Roles role);

}
