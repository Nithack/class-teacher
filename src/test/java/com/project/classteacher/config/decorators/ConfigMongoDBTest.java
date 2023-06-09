package com.project.classteacher.config.decorators;

import com.project.classteacher.config.MongoConfig;
import de.flapdoodle.embed.mongo.MongodExecutable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.io.IOException;


@Import(MongoConfig.class)
public class ConfigMongoDBTest {

    @Autowired
    MongodExecutable mongodExecutable;

    @BeforeEach
    public void tearUp() throws IOException {
        mongodExecutable.start();
    }

    @AfterEach
    public void tearDown() {
        mongodExecutable.stop();
    }



}
