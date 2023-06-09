package com.project.classteacher.infra.dataBase.connection;


import com.project.classteacher.infra.dataBase.model.ClassroomModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassroomMongoDB extends MongoRepository<ClassroomModel, UUID> {
}
