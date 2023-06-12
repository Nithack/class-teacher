package com.project.classteacher.infra.http.controller.secretary;

import com.project.classteacher.application.usecase.classroom.CreateClassroom;
import com.project.classteacher.application.usecase.classroom.UpdateClassroomById;
import com.project.classteacher.application.usecase.teacher.ApproveTeacher;
import com.project.classteacher.application.usecase.teacher.ListUnapprovedTeachers;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.domain.entity.Teacher;
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
import java.util.stream.Collectors;

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

    @PostMapping("/classroom")
    public ResponseEntity<ClassroomOutputDTO> createClassroom(
            @RequestBody CreateClassroomDTO createClassroomDTO
    ) throws ParseException {
        Classroom classroom = createClassroomDTO.toDomain();
        Classroom classroomCreated = createClassroom.execute(classroom);
        return ResponseEntity.ok(ClassroomOutputDTO.toDTO(classroomCreated));
    }

    @PutMapping("/classroom/{id}")
    public ResponseEntity<ClassroomOutputDTO> updateClassroomById(
            @RequestBody @Valid ClassroomUpdateDTO createClassroomDTO,
            @PathVariable("id") UUID id
    ) throws ParseException {
        Classroom classroomUpdated = updateClassroomById.execute(id, createClassroomDTO.toDomain());
        return ResponseEntity.ok(ClassroomOutputDTO.toDTO(classroomUpdated));
    }

}
