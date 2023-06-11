package com.project.classteacher.infra.http.controller.auth;

import com.project.classteacher.application.usecase.login.LoginUser;
import com.project.classteacher.application.usecase.teacher.CreateTeacher;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.infra.http.dtos.CreateTeacherDTO;
import com.project.classteacher.infra.http.dtos.LoginDTO;
import com.project.classteacher.infra.http.dtos.RegisteredUserDTO;
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
    private final CreateTeacher createTeacher;

    // create login endpoint
    @PostMapping("/login")
    public ResponseEntity<Token> login(
            @RequestBody @Valid LoginDTO loginDTO
    ) {

        Token token = loginUser.execute(loginDTO.getEmail(), loginDTO.getPassword());
        return ResponseEntity.ok(token);

    }

    @PostMapping("/register")
    public ResponseEntity<RegisteredUserDTO> register(
            @RequestBody @Valid CreateTeacherDTO teacherDTO
    ) {
        Teacher user = createTeacher.execute(teacherDTO.toDomain());
        return ResponseEntity.ok(RegisteredUserDTO.toDTO(user, Token.encode(user)));
    }
}
