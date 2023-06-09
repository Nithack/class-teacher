package com.project.classteacher.infra.dataBase.connection;


import com.project.classteacher.infra.dataBase.model.ClassroomModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ClassroomMongoDBRepository extends MongoRepository<ClassroomModel, UUID> {
}
