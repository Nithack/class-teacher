package com.project.classteacher.util.mock;

import com.project.classteacher.domain.entity.Password;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.infra.dataBase.mongoDB.model.ClassroomModel;
import com.project.classteacher.infra.dataBase.mongoDB.model.UserModel;
import com.project.classteacher.infra.dataBase.mongoDB.repository.ClassroomMongoDBRepository;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
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
            String approved = ThreadLocalRandom.current().nextBoolean() ? "true" : "false";
            UserModel user = createUser("reference" + i, Roles.TEACHER, Boolean.valueOf(approved));
            userList.add(user);
        }
        return userList;
    }

    public UserModel createUser(String reference, Roles role, Boolean approved) {
        String name = "User" + reference;
        String email = "user" + reference + "@example.com";
        String password = "password" + reference;
        String salt = UUID.randomUUID().toString();

        return userMongoDBRepository.save(
                UserModel.builder()
                        .id(UUID.randomUUID())
                        .name(name)
                        .email(email)
                        .password(Password.create(password, salt).getValue())
                        .role(role.toString())
                        .salt(salt)
                        .approved(approved)
                        .build()
        );

    }

    public List<ClassroomModel> generateMultiplesClassroom(int quantity, UUID teacherId) {
        List<ClassroomModel> classroomList = new java.util.ArrayList<>(Collections.emptyList());
        for (int i = 1; i <= quantity; i++) {
            ClassroomModel classroom = createClassroom(teacherId);
            classroomList.add(classroom);
        }
        return classroomList;
    }

    public ClassroomModel createClassroom(UUID teacherId) {
        String title = "Classroom " + teacherId;
        String description = "Description " + teacherId;
        return classroomMongoDBRepository.save(
                ClassroomModel.builder()
                        .id(UUID.randomUUID())
                        .title(title)
                        .description(description)
                        .teacherId(teacherId)
                        .dayDate(new Date())
                        .build()
        );
    }
}
