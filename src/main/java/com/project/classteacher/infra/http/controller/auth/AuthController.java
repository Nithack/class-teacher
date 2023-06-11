package com.project.classteacher.infra.http.controller.auth;

import com.project.classteacher.application.usecase.login.LoginUser;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.infra.http.dtos.LoginDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginUser loginUser;

    // create login endpoint
    @PostMapping("/login")
    public ResponseEntity<Token> login(
            @RequestBody @Valid LoginDTO loginDTO
    ) {

        Token token = loginUser.execute(loginDTO.getEmail(), loginDTO.getPassword());
        return ResponseEntity.ok(token);

    }

}
