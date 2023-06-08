package com.project.classteacher.application.usecase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveTeacher {

    private Teacher teacher;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldBeCreateNewTeacher() {

        var teacher = new Teacher("Andrey", "andrey@nithack.com", "1234567890");
        userRepository.save(teacher);

    }
}
