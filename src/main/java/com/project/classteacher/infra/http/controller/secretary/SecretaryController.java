package com.project.classteacher.infra.http.controller.secretary;

import com.project.classteacher.application.usecase.teacher.ListUnapprovedTeachers;
import com.project.classteacher.infra.http.dtos.TeacherOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/secretary")
@RequiredArgsConstructor
public class SecretaryController {

    final private ListUnapprovedTeachers listUnapprovedTeachers;

    @GetMapping("/unapproved")
    public ResponseEntity<List<TeacherOutputDTO>> getUnapprovedTeachers() {
        List<TeacherOutputDTO> unapprovedTeachers = listUnapprovedTeachers.execute()
                .stream()
                .map(TeacherOutputDTO::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(unapprovedTeachers);
    }
}
