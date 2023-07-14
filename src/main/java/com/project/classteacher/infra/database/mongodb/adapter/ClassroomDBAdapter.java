package com.project.classteacher.infra.database.mongodb.adapter;

import com.project.classteacher.application.port.ClassroomPort;
import com.project.classteacher.domain.entity.Classroom;
import com.project.classteacher.infra.database.mongodb.model.ClassroomModel;
import com.project.classteacher.infra.database.mongodb.repository.ClassroomMongodbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Component
public class ClassroomDBAdapter implements ClassroomPort {

    @Autowired
    private ClassroomMongodbRepository classroom;

    @Override
    public List<Classroom> listByTeacherId(UUID teacherId) {

        var query = Example.of(
                ClassroomModel.builder()
                        .teacherId(teacherId)
                        .build()
        );

        return this.classroom.findAll(query).stream().map(ClassroomModel::toDomain).filter(Objects::nonNull).toList();
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
