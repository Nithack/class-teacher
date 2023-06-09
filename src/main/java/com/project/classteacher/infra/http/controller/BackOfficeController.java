package com.project.classteacher.infra.http.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class BackOfficeController {

    // create secretary
    @GetMapping("/")
    public ResponseEntity<String> createSecretary() {
        return ResponseEntity.ok().body("secretary created successfully");
    }


}