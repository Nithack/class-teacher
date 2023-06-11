package com.project.classteacher.application.usecase.classroom;

import com.project.classteacher.application.exceptions.ClassroomNotFoundException;
import com.project.classteacher.application.port.ClassroomAdapter;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.domain.mapper.ClassroomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EditClassroomByID {


    private final ClassroomAdapter classroomAdapter;

    public Classroom execute(UUID id, Classroom inputClassroom) {
        var classroom = this.classroomAdapter.getByID(id);

        if (classroom == null) {
            throw new ClassroomNotFoundException(inputClassroom.getId());
        }

        ClassroomMapper.INSTANCE.updateClassroom(classroom, inputClassroom);

        return this.classroomAdapter.save(classroom);

    }
}
