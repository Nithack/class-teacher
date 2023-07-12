package com.project.classteacher.infra.dataBase.mongoDB.adapter;

import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.infra.dataBase.mongoDB.model.UserModel;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserMongoDBAdapter implements UserPort {

    @Autowired
    private UserMongoDBRepository user;

    @Override
    public Teacher save(Teacher teacher) {
        return (Teacher) this.saveModel(teacher);
    }

    @Override
    public Secretary save(Secretary secretary) {
        return (Secretary) saveModel(secretary);
    }

    @Override
    public Teacher findTeacherById(UUID id) {
        UserModel output = this.user.findOne(
                Example.of(
                        UserModel.builder()
                                .id(id)
                                .role(Roles.TEACHER.toString())
                                .build()
                )
        ).orElse(null);
        return output == null ? null : (Teacher) output.toDomain();
    }

    @Override
    public User getByEmail(String email) {
        UserModel output = this.user.findOne(
                Example.of(
                        UserModel.builder()
                                .email(email)
                                .build()
                )
        ).orElse(null);
        return output == null ? null : output.toDomain();
    }

    @Override
    public User findByID(UUID id) {
        UserModel output = this.user.findById(id)
                .orElse(null);
        return output == null ? null : output.toDomain();
    }

    @Override
    public List<User> listByApprovedAndRole(@NotNull Boolean approved, @NotNull Roles role) {

        List<UserModel> result = this.user.findAll(Example.of(
                UserModel.builder()
                        .approved(approved)
                        .role(role.toString())
                        .build()
        ));

        if (result.isEmpty()) return Collections.emptyList();

        return result.stream()
                .map(UserModel::toDomain)
                .collect(Collectors.toList());
    }

    private User saveModel(User user) {
        UserModel userModel = this.user.save(UserModel.toModel(user));
        return userModel.toDomain();
    }
}
