package com.grupp1.school.backend.rest.api.service.mapper;

import com.grupp1.school.backend.rest.api.model.Course;
import com.grupp1.school.backend.rest.api.model.dto.CourseRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.CourseResponseDTO;

public class CourseMapper {
    public static Course toEntity(CourseRequestDTO dto){
        Course entity = new Course();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setMaxStudents(dto.getMaxStudents());
        return entity;
    }

    public static CourseResponseDTO toResponseDTO(Course entity){
        CourseResponseDTO dto = new CourseResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setMaxStudents(entity.getMaxStudents());
        if (entity.getTeacher() != null){
            dto.setTeacher(entity.getTeacher().getName());
        }
        return dto;
    }
}
