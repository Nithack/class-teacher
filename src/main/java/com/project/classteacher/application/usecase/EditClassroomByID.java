package com.project.classteacher.application.usecase;

import com.project.classteacher.application.exceptions.ClassroomNotFoundException;
import com.project.classteacher.application.repository.ClassroomRepository;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.domain.mapper.ClassroomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EditClassroomByID {

    @Autowired
    private ClassroomRepository classroomRepository;

    public Classroom execute(UUID id, Classroom inputClassroom) {
        var classroom = this.classroomRepository.getByID(id);

        if (classroom == null) {
            throw new ClassroomNotFoundException(inputClassroom.getId());
        }

        ClassroomMapper.INSTANCE.updateClassroom(classroom, inputClassroom);

        return this.classroomRepository.save(classroom);

    }
}
