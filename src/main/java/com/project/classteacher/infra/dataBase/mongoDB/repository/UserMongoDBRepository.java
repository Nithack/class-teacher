package com.project.classteacher.infra.dataBase.mongoDB.repository;

import com.project.classteacher.infra.dataBase.mongoDB.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserMongoDBRepository extends MongoRepository<UserModel, UUID> {
}
