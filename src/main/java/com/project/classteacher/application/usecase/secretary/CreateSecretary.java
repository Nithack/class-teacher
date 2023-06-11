package com.project.classteacher.application.usecase.secretary;

import com.project.classteacher.application.port.UserAdapter;
import com.project.classteacher.domain.entity.Secretary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSecretary {

    private final UserAdapter userAdapter;

    public Secretary execute(Secretary secretary) {
        return this.userAdapter.save(secretary);
    }
}
