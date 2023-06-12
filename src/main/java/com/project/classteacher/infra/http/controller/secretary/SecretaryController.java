package com.project.classteacher.infra.http.controller.secretary;

import com.project.classteacher.application.usecase.classroom.CreateClassroom;
import com.project.classteacher.application.usecase.classroom.UpdateClassroomById;
import com.project.classteacher.application.usecase.teacher.ApproveTeacher;
import com.project.classteacher.application.usecase.teacher.ListUnapprovedTeachers;
import com.project.classteacher.infra.http.dtos.ClassroomOutputDTO;
import com.project.classteacher.infra.http.dtos.ClassroomUpdateDTO;
import com.project.classteacher.infra.http.dtos.CreateClassroomDTO;
import com.project.classteacher.infra.http.dtos.TeacherOutputDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/secretary")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SecretaryController {

    final private ListUnapprovedTeachers listUnapprovedTeachers;

    final private ApproveTeacher approveTeacher;
    final private CreateClassroom createClassroom;
    final private UpdateClassroomById updateClassroomById;

    @GetMapping("/unapproved")
    public ResponseEntity<List<TeacherOutputDTO>> getUnapprovedTeachers() {
        var unapprovedTeachers = listUnapprovedTeachers.execute();
        return ResponseEntity.ok(TeacherOutputDTO.toDTO(unapprovedTeachers));
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<TeacherOutputDTO> approveTeacher(
            @PathVariable("id") UUID id
    ) {
        var teacherApproved = approveTeacher.execute(id);
        return ResponseEntity.ok(TeacherOutputDTO.toDTO(teacherApproved));
    }

    @PostMapping("/classroom")
    public ResponseEntity<ClassroomOutputDTO> createClassroom(
            @RequestBody CreateClassroomDTO createClassroomDTO
    ) throws ParseException {
        var classroomCreated = createClassroom.execute(createClassroomDTO.toDomain());
        return ResponseEntity.ok(ClassroomOutputDTO.toDTO(classroomCreated));
    }

    @PutMapping("/classroom/{id}")
    public ResponseEntity<ClassroomOutputDTO> updateClassroomById(
            @RequestBody @Valid ClassroomUpdateDTO createClassroomDTO,
            @PathVariable("id") UUID id
    ) throws ParseException {
        var classroomUpdated = updateClassroomById.execute(id, createClassroomDTO.toDomain());
        return ResponseEntity.ok(ClassroomOutputDTO.toDTO(classroomUpdated));
    }

}
