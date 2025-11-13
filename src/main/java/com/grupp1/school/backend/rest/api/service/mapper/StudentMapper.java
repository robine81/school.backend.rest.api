package com.grupp1.school.backend.rest.api.service.mapper;

import com.grupp1.school.backend.rest.api.model.Student;
import com.grupp1.school.backend.rest.api.model.dto.StudentRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentResponseDTO;

public class StudentMapper {
    public static Student toEntity(StudentRequestDTO dto){
        Student student = new Student();

        student.setStudentId(dto.getStudentId());

        if(!dto.getStudentName().isBlank()){
            student.setStudentName(dto.getStudentName());
        }
        if(!dto.getStudentEmail().isBlank()){
            student.setStudentEmail(dto.getStudentEmail());
        }
        if(dto.getStudentAge() != 0){
            student.setStudentAge(dto.getStudentAge());
        }
        return student;
    }

    public static StudentResponseDTO toResponseDTO(Student student){
        return new StudentResponseDTO(student.getStudentId(), student.getStudentName(), student.getStudentEmail(), student.getStudentAge());
    }

}
