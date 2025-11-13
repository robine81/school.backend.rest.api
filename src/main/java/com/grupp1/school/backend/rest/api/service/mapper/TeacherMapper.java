package com.grupp1.school.backend.rest.api.service.mapper;

import com.grupp1.school.backend.rest.api.model.Course;
import com.grupp1.school.backend.rest.api.model.Teacher;
import com.grupp1.school.backend.rest.api.model.dto.CourseResponseDTO;
import com.grupp1.school.backend.rest.api.model.dto.TeacherRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.TeacherResponseDTO;

import java.util.List;

public class TeacherMapper {
    public static Teacher MapRequestToEntity(TeacherRequestDTO dto){
        return new Teacher(dto.getId(), dto.getName(), dto.getEmail());
    }

//    public static Teacher MapResponseToEntity(TeacherResponseDTO dto){
//        return new Teacher(dto.getId(), dto.getName(), dto.getEmail(), dto.getCourseList().stream().map(CourseMapper::toEntity).toList());
//
//    }

    public static TeacherResponseDTO MapEntityToResponse(Teacher teacher){
        return new TeacherResponseDTO(teacher.getId(), teacher.getName(), teacher.getEmail(), teacher.getCourses().stream().map(CourseMapper::toResponseDTO).toList());

    }

    public static TeacherRequestDTO MapEntityToRequest(Teacher teacher) {
        return new TeacherRequestDTO(teacher.getId(), teacher.getName(), teacher.getEmail(), mapToLong(teacher.getCourses()));
    }

    private static List<Long> mapToLong(List<Course> courses) {
        return courses.stream().map(Course::getId).toList();
    }
}
