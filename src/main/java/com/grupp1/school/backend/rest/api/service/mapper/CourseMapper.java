package com.grupp1.school.backend.rest.api.service.mapper;

import com.grupp1.school.backend.rest.api.model.Course;
import com.grupp1.school.backend.rest.api.model.dto.CourseRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.CourseResponseDTO;

public class CourseMapper {
    public static Course toEntity(CourseRequestDTO dto){
        return new Course(dto.getId(), dto.getName(), dto.getMaxStudents());
    }

    public static CourseResponseDTO toResponseDTO(Course entity){
        return new CourseResponseDTO(entity.getId(), entity.getName(), entity.getMaxStudents());
    }
}
