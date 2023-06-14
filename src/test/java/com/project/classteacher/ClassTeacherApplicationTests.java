package com.project.classteacher;

import com.project.classteacher.config.container.mongodb.MongoContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(MongoContainerConfig.class)
class ClassTeacherApplicationTests {

    @Test
    void contextLoads() {
    }

}
