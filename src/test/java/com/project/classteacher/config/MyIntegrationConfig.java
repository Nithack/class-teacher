package com.project.classteacher.config;

import com.project.classteacher.infra.dataBase.mongoDB.repository.ClassroomMongoDBRepository;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

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
        Clock.fixed(Instant.parse("2014-12-22T10:15:30.00Z"), ZoneId.of("UTC"));
    }

}
