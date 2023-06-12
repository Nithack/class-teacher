package com.project.classteacher.application.usecase.classroom;

import com.project.classteacher.application.exceptions.ClassroomNotFoundException;
import com.project.classteacher.application.port.ClassroomPort;
import com.project.classteacher.application.util.mapper.ClassroomMapper;
import com.project.classteacher.domain.entity.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateClassroomById {


    private final ClassroomPort classroomPort;

    public Classroom execute(UUID id, Classroom inputClassroom) {

        var teacher = this.classroomPort.getByID(inputClassroom.getId());
        var classroom = this.classroomPort.getByID(id);

        if (classroom == null) {
            throw new ClassroomNotFoundException(inputClassroom.getId());
        }

        ClassroomMapper.INSTANCE.updateClassroom(classroom, inputClassroom);

        return this.classroomPort.save(classroom);

    }
}