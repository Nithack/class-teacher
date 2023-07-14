package com.project.classteacher.config;

import com.project.classteacher.config.container.mongodb.MongoContainerConfig;
import com.project.classteacher.infra.database.mongodb.repository.ClassroomMongodbRepository;
import com.project.classteacher.infra.database.mongodb.repository.UserMongodbRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

import java.util.Objects;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(MongoContainerConfig.class)
@EnableCaching()
public class MyIntegrationConfig {
    @Autowired
    private UserMongodbRepository userMongoDBRepository;

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private ClassroomMongodbRepository classroomMongoDBRepository;


    @BeforeEach
    public void setUp() {
        userMongoDBRepository.deleteAll();
        classroomMongoDBRepository.deleteAll();
        for (String name : cacheManager.getCacheNames()) {
            Objects.requireNonNull(cacheManager.getCache(name)).clear();
        }
    }

}
