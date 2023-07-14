package com.project.classteacher.infra.database.mongodb.repository;


import com.project.classteacher.infra.database.mongodb.model.ClassroomModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassroomMongodbRepository extends MongoRepository<ClassroomModel, UUID> {
}
