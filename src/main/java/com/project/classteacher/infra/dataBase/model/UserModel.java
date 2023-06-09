package com.project.classteacher.infra.dataBase.model;

import com.project.classteacher.application.factory.UserFactory;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.domain.enums.Roles;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
@Builder
@Document(collection = "user")
public class UserModel {

    @MongoId
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String salt;
    private String aproved;

    public static UserModel toModel(User user) {
        return UserModel.builder()
                .id(user.getId().toString())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().toString())
                .salt(user.getSalt())
                .aproved(user.getApproved().toString())
                .build();
    }

    public User toDomain() {
        return UserFactory.buildExistingUser(
                UUID.fromString(this.id),
                this.name,
                this.email,
                this.password,
                Roles.valueOf(this.role),
                this.salt,
                this.aproved
        );
    }

}
