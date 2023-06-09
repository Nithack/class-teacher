package com.project.classteacher.application.repository;

import com.project.classteacher.domain.entity.Classroom;

import java.util.List;
import java.util.UUID;

public interface ClassroomServiceRepository {
    List<Classroom> listByTeacherId(UUID teacherId);

    Classroom save(Classroom classroom);

    Classroom getByID(UUID id);

}
