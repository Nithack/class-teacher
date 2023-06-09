package com.project.classteacher.infra.dataBase.connection;

import com.project.classteacher.infra.dataBase.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserMongoDBRepository extends MongoRepository<UserModel, UUID> {
}
