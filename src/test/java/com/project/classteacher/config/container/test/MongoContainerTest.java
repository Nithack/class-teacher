package com.project.classteacher.config.container.test;

import com.project.classteacher.config.container.mongodb.MongoContainerConfig;
import com.project.classteacher.infra.database.mongodb.repository.ClassroomMongodbRepository;
import com.project.classteacher.infra.database.mongodb.repository.UserMongodbRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(MongoContainerConfig.class)
class MongoContainerTest {

    @Autowired
    private UserMongodbRepository userMongoDBRepository;

    @Autowired
    private ClassroomMongodbRepository classroomMongoDBRepository;

    @Test
    @DisplayName("MongoDBContainer should be test repository")
    void should_be_test_repository() {
        System.out.println("userMongoDBRepository = " + userMongoDBRepository);
        System.out.println("classroomMongoDBRepository = " + classroomMongoDBRepository);

        assertAll(
                () -> assertNotNull(userMongoDBRepository),
                () -> assertNotNull(classroomMongoDBRepository)
        );
    }
}
