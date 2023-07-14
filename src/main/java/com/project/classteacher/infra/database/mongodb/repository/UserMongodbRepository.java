package com.project.classteacher.infra.database.mongodb.repository;

import com.project.classteacher.infra.database.mongodb.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserMongodbRepository extends MongoRepository<UserModel, UUID> {
}
