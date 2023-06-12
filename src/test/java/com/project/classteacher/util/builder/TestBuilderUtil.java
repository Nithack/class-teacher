package com.project.classteacher.util.builder;

import com.project.classteacher.domain.entity.*;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.infra.dataBase.mongoDB.model.UserModel;
import com.project.classteacher.infra.http.dtos.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.getProperty;

public class TestBuilderUtil {

    @Value("${basic.salt:salt}")
    static final String DEFAULT_SALT = "salt";
    static final String INSURER;
    static final String TOKEN_KEY;

    static {
        INSURER = getProperty("jwt.ensurer", "class-teacher");
        TOKEN_KEY = getProperty("jwt.token_key", "qwertyuiopasdfghjklzxcvbnm123456");
    }

    public static Teacher generateTeacher() {
        return createTeacher(
                generateId(),
                "Secretary 1",
                "secretary1@gmail.com",
                "123456",
                Boolean.valueOf(ThreadLocalRandom.current().nextBoolean() ? "true" : "false")
        );
    }

    public static Teacher generateApprovedTeacher() {
        return createTeacher(
                generateId(),
                "Secretary 1",
                "secretary1@gmail.com",
                "123456",
                Boolean.valueOf(ThreadLocalRandom.current().nextBoolean() ? "true" : "false")
        );
    }

    public static Teacher generateUnapprovedTeacher() {
        return createTeacher(
                generateId(),
                "Secretary 1",
                "secretary1@gmail.com",
                "123456",
                false
        );
    }

    public static Teacher createTeacher(UUID id, String name, String email, String password, Boolean approved) {
        return Teacher.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(Password.create(password, DEFAULT_SALT))
                .approved(approved)
                .build();
    }

    public static Secretary generateSecretary() {
        return createSecretary(
                generateId(),
                "Teacher 1",
                "teacher1@gmail.com",
                "123456"
        );
    }

    public static Secretary createSecretary(UUID id, String name, String email, String password) {
        return Secretary.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(Password.create(password, DEFAULT_SALT))
                .build();
    }

    public static UUID generateId() {
        return UUID.randomUUID();
    }

    public static Classroom generateClassroom() {
        return createClassroom(
                generateId(),
                "Classroom 1",
                "Classroom 1 description",
                new Date(),
                generateId()
        );
    }

    public static Classroom createClassroom(UUID uuid, String title, String description, Date dayDate, UUID teacherId) {
        return Classroom.builder()
                .id(uuid)
                .title(title)
                .description(description)
                .dayDate(dayDate)
                .teacherId(teacherId)
                .build();
    }

    public static Token generateToken() {
        return createToken(generateUser());
    }

    public static Token createToken(User user) {
        return Token.encode(
                user
        );
    }

    public static User generateUser() {
        return createUser(
                generateId(),
                "test",
                "test@gmail.com",
                "123456",
                Roles.TEACHER,
                false
        );
    }

    public static User createUser(UUID id, String name, String email, String password, Roles role, Boolean approved) {
        return new User(
                id,
                name,
                email,
                Password.create(password, DEFAULT_SALT),
                role,
                approved
        );
    }

    public static String createExpiredToken(User user) {

        Date expirationDate = new Date(System.currentTimeMillis() - 1000);

        return Jwts.builder()
                .setIssuer(INSURER)
                .setSubject(user.getName())
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .claim("role", user.getRole())
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(TOKEN_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public static LoginDTO createLoginDTO(String name, String password) {
        return LoginDTO.builder()
                .email(name)
                .password(password)
                .build();
    }

    public static Optional<UserModel> createOptionalUserModel(User user) {
        return Optional.ofNullable(UserModel.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().toString())
                .id(user.getId())
                .name(user.getName())
                .salt(user.getSalt())
                .build()
        );
    }

    public static UserDTO createUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static CreateTeacherDTO createTeacherDTO(User user) {
        return CreateTeacherDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static UserModel createUserModel(User user) {
        return UserModel.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().toString())
                .id(user.getId())
                .name(user.getName())
                .salt(user.getSalt())
                .build();
    }

    public static CreateClassroomDTO createClassroomDTO(UUID uuid, String literature, String thisIsALiteratureClass, Date dayDate, UUID id) {
        return CreateClassroomDTO.builder()
                .title(literature)
                .description(thisIsALiteratureClass)
                .dayDate(dayDate)
                .teacherId(id)
                .build();
    }

    public static ClassroomUpdateDTO createClassroomUpdateDTO(String title, String description, UUID teacherId, @Nullable Date dayDate) {
        return ClassroomUpdateDTO.builder()
                .title(title)
                .description(description)
                .teacherId(teacherId)
                .dayDate(dayDate)
                .build();
    }
}
