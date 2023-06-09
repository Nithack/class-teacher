package com.project.classteacher.application.repository;

import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.infra.dataBase.model.ClassroomModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ClassroomRepository extends MongoRepository<ClassroomModel, UUID> {
    List<Classroom> listByTeacherId(UUID teacherId);
    Classroom save(Classroom classroom);
    Classroom getByID(UUID id);
}
