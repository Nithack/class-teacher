package com.project.classteacher.application.port;

import com.project.classteacher.domain.entity.Classroom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassroomServiceRepository {
    List<Classroom> listByTeacherId(UUID teacherId);

    Classroom save(Classroom classroom);

    Classroom getByID(UUID id);

}
