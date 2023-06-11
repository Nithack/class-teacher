package com.project.classteacher.application.util.mapper;

import com.project.classteacher.domain.entity.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClassroomMapper {
    ClassroomMapper INSTANCE = Mappers.getMapper(ClassroomMapper.class);

    void updateClassroom(@MappingTarget Classroom target, Classroom source);
}