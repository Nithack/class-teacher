package com.project.classteacher.application.repository;

import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.infra.dataBase.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository  extends MongoRepository<UserModel, UUID> {
    Teacher saveTeacher(Teacher teacher);
    Secretary saveSecretary(Secretary secretary);
    Teacher findTeacherById(UUID id);
    User getByEmail(String email);
    List<User> listByApprovedAndRole(Boolean approved, Roles role);
}
