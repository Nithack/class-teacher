package com.project.classteacher.infra.http.controller.secretary;

import com.project.classteacher.application.usecase.classroom.CreateClassroom;
import com.project.classteacher.application.usecase.classroom.UpdateClassroomById;
import com.project.classteacher.application.usecase.teacher.ApproveTeacher;
import com.project.classteacher.application.usecase.teacher.ListUnapprovedTeachers;
import com.project.classteacher.infra.http.dtos.ClassroomOutputDTO;
import com.project.classteacher.infra.http.dtos.ClassroomUpdateDTO;
import com.project.classteacher.infra.http.dtos.CreateClassroomDTO;
import com.project.classteacher.infra.http.dtos.TeacherOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/secretary")
@RequiredArgsConstructor
@Tag(
        name = "Secretary",
        description = "Endpoints for secretary manager teachers and classrooms"
)
@SecurityRequirement(name = "bearerAuth")
public class SecretaryController {

    private final ListUnapprovedTeachers listUnapprovedTeachers;

    private final ApproveTeacher approveTeacher;
    private final CreateClassroom createClassroom;
    private final UpdateClassroomById updateClassroomById;

    @Operation(
            summary = "Get unapproved teachers",
            description = "Get all teachers that are not approved yet"
    )
    @GetMapping("/teacher/unapproved")
    @Cacheable("list-unapproved-teachers")
    public ResponseEntity<List<TeacherOutputDTO>> getUnapprovedTeachers() {
        var unapprovedTeachers = listUnapprovedTeachers.execute();
        return ResponseEntity.ok(TeacherOutputDTO.toDTO(unapprovedTeachers));
    }

    @Operation(
            summary = "Approve teacher",
            description = "Approve a teacher by id"
    )
    @PostMapping("/teacher/approve/{id}")
    @CacheEvict(value = {"list-unapproved-teachers", "list-teacher-classrooms"}, allEntries = true)
    public ResponseEntity<TeacherOutputDTO> approveTeacher(
            @PathVariable("id") UUID id
    ) {
        var teacherApproved = approveTeacher.execute(id);
        return ResponseEntity.ok(TeacherOutputDTO.toDTO(teacherApproved));
    }

    @Operation(
            summary = "Create classroom",
            description = "Create a new classroom"
    )
    @PostMapping("/classroom")
    @CacheEvict("list-teacher-classrooms")
    public ResponseEntity<ClassroomOutputDTO> createClassroom(
            @RequestBody CreateClassroomDTO createClassroomDTO
    ) {
        var classroomCreated = createClassroom.execute(createClassroomDTO.toDomain());
        return ResponseEntity.ok(ClassroomOutputDTO.toDTO(classroomCreated));
    }

    @Operation(
            summary = "Update classroom",
            description = "Update a existing classroom by id"
    )
    @PutMapping("/classroom/{id}")
    @CacheEvict("list-teacher-classrooms")
    public ResponseEntity<ClassroomOutputDTO> updateClassroomById(
            @RequestBody @Valid ClassroomUpdateDTO createClassroomDTO,
            @PathVariable("id") UUID id
    ) {
        var classroomUpdated = updateClassroomById.execute(id, createClassroomDTO.toDomain());
        return ResponseEntity.ok(ClassroomOutputDTO.toDTO(classroomUpdated));
    }

}
