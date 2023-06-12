package com.project.classteacher.config;

import com.project.classteacher.infra.dataBase.mongoDB.repository.ClassroomMongoDBRepository;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MyIntegrationConfig {
    private final String DEFAULT_DATE = "2021-01-01";
    @Autowired
    private UserMongoDBRepository userMongoDBRepository;
    @MockBean
    private Date date;

    @Autowired
    private ClassroomMongoDBRepository classroomMongoDBRepository;


    @BeforeEach
    public void setUp() {
        userMongoDBRepository.deleteAll();
        classroomMongoDBRepository.deleteAll();
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.FEBRUARY, 20, 12, 20, 0);
        when(date.getTime()).thenReturn(
                cal.getTime().getTime()
        );
    }

}
