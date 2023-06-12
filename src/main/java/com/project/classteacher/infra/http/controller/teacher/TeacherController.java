package com.project.classteacher.infra.http.controller.teacher;

import com.project.classteacher.application.usecase.classroom.ListClassroomByTeacherID;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.domain.entity.DecodeToken;
import com.project.classteacher.infra.http.dtos.ClassroomOutputDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TeacherController {

    private final ListClassroomByTeacherID listClassroomByTeacherID;

    @GetMapping("/classroom")
    public ResponseEntity<List<ClassroomOutputDTO>> getTeacherClassroom(@AuthenticationPrincipal DecodeToken userDetails) {

        List<Classroom> classrooms = listClassroomByTeacherID.execute(userDetails.getId());

        List<ClassroomOutputDTO> classroomDTOs = classrooms != null ? classrooms.stream()
                .map(ClassroomOutputDTO::toDTO)
                .toList() : Collections.emptyList();

        return ResponseEntity.ok(classroomDTOs);
    }


}
