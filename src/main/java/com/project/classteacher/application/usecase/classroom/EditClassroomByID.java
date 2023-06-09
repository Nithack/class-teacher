package com.project.classteacher.application.usecase.classroom;

import com.project.classteacher.application.exceptions.ClassroomNotFoundException;
import com.project.classteacher.application.repository.ClassroomServiceRepository;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.domain.mapper.ClassroomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EditClassroomByID {


    private final ClassroomServiceRepository classroomServiceRepository;

    public Classroom execute(UUID id, Classroom inputClassroom) {
        var classroom = this.classroomServiceRepository.getByID(id);

        if (classroom == null) {
            throw new ClassroomNotFoundException(inputClassroom.getId());
        }

        ClassroomMapper.INSTANCE.updateClassroom(classroom, inputClassroom);

        return this.classroomServiceRepository.save(classroom);

    }
}
