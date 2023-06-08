package com.project.classteacher;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;

@Import(MongoConfig.class)
@ActiveProfiles("test")
public @interface ConfigContainersTest {
}
