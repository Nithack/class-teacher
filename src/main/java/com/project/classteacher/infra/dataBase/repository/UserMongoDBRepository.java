package com.project.classteacher.infra.dataBase.repository;

import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.domain.entity.Secretary;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.infra.dataBase.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface UserMongoDBRepository  extends UserRepository{
    @Override
    default Teacher saveTeacher(Teacher teacher) {
        return (Teacher) saveModel(teacher);
    }

    @Override
    default Secretary saveSecretary(Secretary secretary) {
        return (Secretary) saveModel(secretary);
    }

    @Override
    default Teacher findTeacherById(UUID id) {
        UserModel output = findById(id).orElse(null);
        return output == null ? null : (Teacher) output.toDomain();
    }

    @Override
    default User getByEmail(String email) {
        UserModel output = findByEmail(email);
        return output == null ? null : output.toDomain();
    }

    @Override
    default List<User> listByApprovedAndRole(Boolean approved, Roles role) {
        List<UserModel> result = listUserByApprovedAndRole(approved, role);

        if (result.isEmpty()) return null;

        return result.stream()
                .map(UserModel::toDomain)
                .collect(Collectors.toList());
    }

    default User saveModel(User user) {
        UserModel userModel = this.save(UserModel.toModel(user));
        return userModel.toDomain();
    }

    @Query(value = "{'email': ?0}")
    UserModel findByEmail(String email);

    @Query(value = "{'approved': ?0, 'role': ?1}")
    List<UserModel> listUserByApprovedAndRole(Boolean approved, Roles role);
}
