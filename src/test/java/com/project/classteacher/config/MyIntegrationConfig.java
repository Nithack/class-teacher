package com.project.classteacher.config;

import com.project.classteacher.infra.dataBase.mongoDB.repository.ClassroomMongoDBRepository;
import com.project.classteacher.infra.dataBase.mongoDB.repository.UserMongoDBRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@EnableCaching()
public class MyIntegrationConfig {
    @Autowired
    private UserMongoDBRepository userMongoDBRepository;

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private ClassroomMongoDBRepository classroomMongoDBRepository;


    @BeforeEach
    public void setUp() {
        userMongoDBRepository.deleteAll();
        classroomMongoDBRepository.deleteAll();
        for (String name : cacheManager.getCacheNames()) {
            Objects.requireNonNull(cacheManager.getCache(name)).clear();
        }
    }

}
