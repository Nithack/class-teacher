package com.project.classteacher.infra.dataBase.mongoDB.repository;


import com.project.classteacher.infra.dataBase.mongoDB.model.ClassroomModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ClassroomMongoDBRepository extends MongoRepository<ClassroomModel, UUID> {
}
