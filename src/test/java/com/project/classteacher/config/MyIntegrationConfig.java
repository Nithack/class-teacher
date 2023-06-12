package com.project.classteacher.config;

import com.project.classteacher.infra.dataBase.mongoDB.repository.ClassroomMongoDBRepository;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MyIntegrationConfig {
    @Autowired
    private UserMongoDBRepository userMongoDBRepository;


    @Autowired
    private ClassroomMongoDBRepository classroomMongoDBRepository;

    @MockBean
    private Date mockedDate;


    @BeforeEach
    public void setUp() {
        userMongoDBRepository.deleteAll();
        classroomMongoDBRepository.deleteAll();
        when(mockedDate.getTime()).thenReturn(1623435600000L);
    }

}
