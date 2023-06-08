package com.project.classteacher;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Import(MongoConfig.class)
@ActiveProfiles("test")
public @interface ConfigContainersTest {
}
