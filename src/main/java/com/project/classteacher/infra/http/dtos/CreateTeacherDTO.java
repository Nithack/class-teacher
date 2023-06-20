package com.project.classteacher.infra.http.dtos;

import com.project.classteacher.application.factory.UserFactory;
import com.project.classteacher.domain.entity.Teacher;
import com.project.classteacher.domain.enums.Roles;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTeacherDTO {
    private String name;
    @Email
    private String email;
    private String password;

    public Teacher toDomain() {
        return (Teacher) UserFactory.createUser(null, name, email, password, Roles.TEACHER);
    }
}
