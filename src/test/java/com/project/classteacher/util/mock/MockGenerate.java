package com.project.classteacher.util.mock;

import com.project.classteacher.domain.entity.Password;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.infra.dataBase.mongoDB.model.UserModel;
import com.project.classteacher.infra.dataBase.mongoDB.repository.ClassroomMongoDBRepository;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class MockGenerate {

    @Autowired
    private UserMongoDBRepository userMongoDBRepository;
    @Autowired
    private ClassroomMongoDBRepository classroomMongoDBRepository;

    public List<UserModel> generateMultiplesTeachers(int quantity) {
        List<UserModel> userList = new java.util.ArrayList<>(Collections.emptyList());
        for (int i = 1; i <= quantity; i++) {
            UserModel user = userMongoDBRepository.save(createUser("reference" + i, Roles.TEACHER));
            userList.add(user);
        }
        return userList;
    }

    public UserModel createUser(String reference, Roles role) {
        String name = "User" + reference;
        String email = "user" + reference + "@example.com";
        String password = "password" + reference;
        String salt = UUID.randomUUID().toString();
        String approved = ThreadLocalRandom.current().nextBoolean() ? "true" : "false";

        return UserModel.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .email(email)
                .password(Password.create(password, salt).getValue())
                .role(role.toString())
                .salt(salt)
                .approved(approved)
                .build();
    }
}
