package com.project.classteacher.application.worker;

import com.project.classteacher.application.usecase.secretary.CreateSecretary;
import com.project.classteacher.domain.entity.Password;
import com.project.classteacher.domain.entity.Secretary;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class NewSecretaryWorker {


    private final CreateSecretary createSecretary;

    @Value("${manager.SECRETARY_USER}")
    private String SECRETARY_USER;
    @Value("${manager.SECRETARY_PASSWORD}")
    private String SECRETARY_PASSWORD;
    @Value("${jwt.salt}")
    private String DEFAULT_SALT;

    @PostConstruct
    public void execute() {


        var secretary = Secretary.builder()
                .id(UUID.randomUUID())
                .name(SECRETARY_USER)
                .email(SECRETARY_USER)
                .password(Password.create(
                        SECRETARY_PASSWORD,
                        DEFAULT_SALT
                ))
                .build();

        this.createSecretary.execute(secretary);
    }
}
