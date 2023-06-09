package com.project.classteacher.application.usecase.classroom;

import com.project.classteacher.application.exceptions.TeacherNotFoundException;
import com.project.classteacher.application.repository.ClassroomServiceRepository;
import com.project.classteacher.application.repository.UserServiceRepository;
import com.project.classteacher.domain.entity.Classroom;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateClassroom {

    private final ClassroomServiceRepository classroomServiceRepository;
    private final UserServiceRepository userServiceRepository;

    public Classroom execute(@NotNull Classroom classroom) throws TeacherNotFoundException {
        var teacher = this.userServiceRepository.findById(classroom.getTeacherId());

        if (teacher == null) throw new TeacherNotFoundException(classroom.getTeacherId());

        return this.classroomServiceRepository.save(classroom);
    }
}