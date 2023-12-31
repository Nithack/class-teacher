package com.project.classteacher.application.port;

import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserPort {
    Teacher save(Teacher teacher);

    Teacher findTeacherById(UUID teacherId);

    Secretary save(Secretary secretary);

    User getByEmail(String email);

    List<User> listByApprovedAndRole(Boolean approved, Roles role);

    User findByID(UUID id);

}
