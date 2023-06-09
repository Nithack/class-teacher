package com.project.classteacher.infra.dataBase.repository;

import com.project.classteacher.application.repository.ClassroomServiceRepository;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.infra.dataBase.connection.ClassroomMongoDB;
import com.project.classteacher.infra.dataBase.model.ClassroomModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Component
public class ClassroomDBServiceRepository implements ClassroomServiceRepository {

    @Autowired
    private ClassroomMongoDB classroom;

    @Override
    public List<Classroom> listByTeacherId(@NotNull UUID teacherId) {

        var query = Example.of(
                ClassroomModel.builder()
                        .teacherId(teacherId.toString())
                        .build()
        );

        List<Classroom> result = this.classroom.findAll(query).stream().map(ClassroomModel::toDomain).filter(Objects::nonNull).toList();
        if (result.isEmpty()) return null;

        return result;
    }

    @Override
    public Classroom save(Classroom classroom) {
        ClassroomModel classroomModel = this.classroom.save(ClassroomModel.toModel(classroom));
        return classroomModel.toDomain();
    }

    @Override
    public Classroom getByID(UUID id) {
        ClassroomModel classroomModel = this.classroom.findById(id).orElse(null);
        return classroomModel == null ? null : classroomModel.toDomain();
    }
}
