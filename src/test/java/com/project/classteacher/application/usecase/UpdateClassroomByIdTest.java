package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.ClassroomNotFoundException;
import com.project.classteacher.application.port.ClassroomPort;
import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.application.usecase.classroom.UpdateClassroomById;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Edit Classroom By ID Test")
@SpringBootTest(classes = UpdateClassroomById.class)
final class UpdateClassroomByIdTest {

    UUID DEFAULT_UUID;
    @MockBean
    private ClassroomPort classroomPort;
    @MockBean
    private UserPort userPort;
    @Autowired
    private UpdateClassroomById updateClassroomById;

    @BeforeEach
    public void setUp() {
        this.DEFAULT_UUID = TestBuilderUtil.generateId();
    }

    @Test
    @DisplayName("should be list all classroom by teacher id")
    public void should_be_list_all_classroom_by_teacher_id() {

        var teacher = TestBuilderUtil.generateApprovedTeacher();
        var classroomLiterature = TestBuilderUtil.createClassroom(
                this.DEFAULT_UUID,
                "Literatura",
                "Aula focada no ensino da literatura",
                new Date(),
                teacher.getId()
        );
        var inputChanges = TestBuilderUtil.createClassroom(
                null,
                null,
                "Aula focada no ensino da literatura e da lingua portuguesa",
                new Date(),
                null
        );

        var classroomSavedMock = TestBuilderUtil.createClassroom(
                classroomLiterature.getId(),
                classroomLiterature.getTitle(),
                inputChanges.getDescription(),
                inputChanges.getDayDate(),
                classroomLiterature.getTeacherId()
        );
        Mockito.when(userPort.findTeacherById(teacher.getId())).thenReturn(teacher);
        Mockito.when(classroomPort.getByID(classroomLiterature.getId())).thenReturn(classroomLiterature);
        Mockito.when(classroomPort.save(Mockito.any(Classroom.class))).thenAnswer(invocation -> classroomSavedMock);

        
        var classroomSaved = updateClassroomById.execute(classroomLiterature.getId(), inputChanges);

        assertEquals(classroomSaved.getId(), classroomLiterature.getId());
        assertEquals(classroomSaved.getTitle(), classroomLiterature.getTitle());
        assertEquals(classroomSaved.getTeacherId(), classroomLiterature.getTeacherId());
        assertEquals(classroomSaved.getDescription(), inputChanges.getDescription());
        assertEquals(classroomSaved.getDayDate(), inputChanges.getDayDate());
    }

    @Test()
    @DisplayName("should be throw exception when teacher not found")
    public void should_be_throw_exception_when_teacher_not_found() {

        var inputChanges = TestBuilderUtil.createClassroom(
                null,
                null,
                "Aula focada no ensino da literatura e da lingua portuguesa",
                new Date(),
                null
        );

        Mockito.when(userPort.findTeacherById(this.DEFAULT_UUID)).thenReturn(null);

        Assertions.assertThrows(ClassroomNotFoundException.class, () -> updateClassroomById.execute(this.DEFAULT_UUID, inputChanges));
    }
}
