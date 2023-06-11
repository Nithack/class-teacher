package com.project.classteacher.config;

import com.project.classteacher.infra.dataBase.mongoDB.repository.ClassroomMongoDBRepository;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MyIntegrationConfig {
    @Autowired
    private UserMongoDBRepository userMongoDBRepository;


    @Autowired
    private ClassroomMongoDBRepository classroomMongoDBRepository;

    @BeforeEach
    public void setUp() {
        userMongoDBRepository.deleteAll();
        classroomMongoDBRepository.deleteAll();
    }

}
