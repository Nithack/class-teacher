package com.project.classteacher.infra.http.controller.secretary;

import com.project.classteacher.application.usecase.teacher.ApproveTeacher;
import com.project.classteacher.application.usecase.teacher.ListUnapprovedTeachers;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.infra.http.dtos.TeacherOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/secretary")
@RequiredArgsConstructor
public class SecretaryController {

    final private ListUnapprovedTeachers listUnapprovedTeachers;

    final private ApproveTeacher approveTeacher;

    @GetMapping("/unapproved")
    public ResponseEntity<List<TeacherOutputDTO>> getUnapprovedTeachers() {
        List<TeacherOutputDTO> unapprovedTeachers = listUnapprovedTeachers.execute()
                .stream()
                .map(TeacherOutputDTO::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(unapprovedTeachers);
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<TeacherOutputDTO> approveTeacher(
            @PathVariable("id") UUID id
    ) {
        Teacher teacherApproved = approveTeacher.execute(id);
        return ResponseEntity.ok(TeacherOutputDTO.toDTO(teacherApproved));
    }

}
