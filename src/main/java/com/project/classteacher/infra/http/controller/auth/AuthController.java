package com.project.classteacher.infra.http.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // create login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login");
    }

}
