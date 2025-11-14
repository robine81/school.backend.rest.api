package com.grupp1.school.backend.rest.api.service.mapper;

import com.grupp1.school.backend.rest.api.model.Teacher;
import com.grupp1.school.backend.rest.api.model.dto.TeacherRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.TeacherResponseDTO;

import java.util.stream.Collectors;


public class TeacherMapper {
    public static Teacher toEntity(TeacherRequestDTO dto){
        Teacher entity = new Teacher();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public static TeacherResponseDTO toResponse(Teacher entity){
        TeacherResponseDTO dto = new TeacherResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setCourses(entity.getCourses().stream().map(CourseMapper::mapEntityToTeacherCourseList).collect(Collectors.toSet()));
        return dto;
    }
}
