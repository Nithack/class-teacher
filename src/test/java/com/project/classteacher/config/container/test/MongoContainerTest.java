package com.project.classteacher.config.container.test;

import com.project.classteacher.config.container.mongodb.MongoContainerConfig;
import com.project.classteacher.infra.dataBase.mongoDB.repository.ClassroomMongoDBRepository;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(MongoContainerConfig.class)
public class MongoContainerTest {

    @Autowired
    private UserMongoDBRepository userMongoDBRepository;

    @Autowired
    private ClassroomMongoDBRepository classroomMongoDBRepository;

    @Test
    @DisplayName("MongoDBContainer should be test repository")
    public void should_be_test_repository() {
        System.out.println("userMongoDBRepository = " + userMongoDBRepository);
        System.out.println("classroomMongoDBRepository = " + classroomMongoDBRepository);

        assertAll(
                () -> assertNotNull(userMongoDBRepository),
                () -> assertNotNull(classroomMongoDBRepository)
        );
    }
}
