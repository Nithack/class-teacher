package com.project.classteacher;

import com.project.classteacher.application.repository.ClassroomServiceRepository;
import com.project.classteacher.application.repository.UserServiceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication()
@EnableMongoRepositories(basePackageClasses = {UserServiceRepository.class, ClassroomServiceRepository.class})
public class ClassTeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassTeacherApplication.class, args);
    }

}
