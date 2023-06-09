package com.project.classteacher.infra.dataBase.repository;

import com.project.classteacher.application.repository.ClassroomRepository;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.infra.dataBase.model.ClassroomModel;
import java.text.ParseException;

import com.project.classteacher.infra.dataBase.model.UserModel;
import lombok.SneakyThrows;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public interface ClassroomMongoDBRepository extends ClassroomRepository {
    @Override
    default List<Classroom> listByTeacherId(UUID teacherId) {
        List<ClassroomModel> result = listByTeacherId(teacherId.toString());

        if (result.isEmpty()) return null;

        return result.stream()
                .map(ClassroomModel::toDomain)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    };
    @Override
    default Classroom save(Classroom classroom){
        ClassroomModel classroomModel = this.save(ClassroomModel.toModel(classroom));
        return classroomModel.toDomain();
    }
    @Override
    Classroom getByID(UUID id);

    @Query(value = "{'teacherId': ?0}")
    List<ClassroomModel> listByTeacherId(String teacherId);

}
