package com.project.classteacher.application.usecase.secretary;

import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.domain.entity.Secretary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSecretary {

    private final UserPort userPort;

    public Secretary execute(Secretary secretary) {
        return this.userPort.save(secretary);
    }
}
