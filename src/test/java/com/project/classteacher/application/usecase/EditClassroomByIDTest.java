package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.ClassroomNotFoundException;
import com.project.classteacher.application.repository.ClassroomRepository;
import com.project.classteacher.application.repository.UserRepository;
import com.project.classteacher.config.decorators.ConfigContainersTest;
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

import java.text.ParseException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Edit Classroom By ID Test")
@SpringBootTest(classes = EditClassroomByID.class)
final class EditClassroomByIDTest {

    @MockBean
    private ClassroomRepository classroomRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private EditClassroomByID editClassroomByID;
    UUID DEFAULT_UUID;

    @BeforeEach
    public void setUp() {
        this.DEFAULT_UUID = TestBuilderUtil.generateId();
    }

    @Test
    @DisplayName("should be list all classroom by teacher id")
    public void should_be_list_all_classroom_by_teacher_id() throws ParseException {

        var teacher = TestBuilderUtil.generateTeacher();
        var classroomLiterature = TestBuilderUtil.createClassroom(
                this.DEFAULT_UUID,
                "Literatura",
                "Aula focada no ensino da literatura",
                Classroom.dateFormat("2021-10-10T11:15:00.000Z"),
                teacher.getId()
        );
        var inputChanges = TestBuilderUtil.createClassroom(
                null,
                null,
                "Aula focada no ensino da literatura e da lingua portuguesa",
                Classroom.dateFormat("2021-10-10T08:15:00.000Z"),
                null
        );
        Mockito.when(userRepository.findTeacherById(teacher.getId())).thenReturn(teacher);
        Mockito.when(classroomRepository.getByID(classroomLiterature.getId())).thenReturn(classroomLiterature);
        Mockito.when(classroomRepository.save(Mockito.any(Classroom.class))).thenAnswer(invocation -> invocation.getArgument(0));
        var classroomSaved = editClassroomByID.execute(classroomLiterature.getId(), inputChanges);

        assertEquals(classroomSaved.getId(), classroomLiterature.getId());
        assertEquals(classroomSaved.getTitle(), classroomLiterature.getTitle());
        assertEquals(classroomSaved.getTeacherId(), classroomLiterature.getTeacherId());
        assertEquals(classroomSaved.getDescription(), inputChanges.getDescription());
        assertEquals(classroomSaved.getDayDate(), inputChanges.getDayDate());
    }

    @Test()
    @DisplayName("should be throw exception when teacher not found")
    public void should_be_throw_exception_when_teacher_not_found() throws ParseException {

        var inputChanges = TestBuilderUtil.createClassroom(
                null,
                null,
                "Aula focada no ensino da literatura e da lingua portuguesa",
                Classroom.dateFormat("2021-10-10T08:15:00.000Z"),
                null
        );

        Mockito.when(userRepository.findTeacherById(this.DEFAULT_UUID)).thenReturn(null);

        Assertions.assertThrows(ClassroomNotFoundException.class, () -> {
            editClassroomByID.execute(this.DEFAULT_UUID, inputChanges);
        });
    }
}
