package com.grupp1.school.backend.rest.api.service;

import com.fasterxml.jackson.databind.BeanProperty;
import com.grupp1.school.backend.rest.api.model.Student;
import com.grupp1.school.backend.rest.api.model.dto.StudentDTO;
import com.grupp1.school.backend.rest.api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    private final StudentRepository repository;

    private final AtomicLong idCounter = new AtomicLong();

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<StudentDTO> getAll() {
        List<Student> students = repository.findAll();
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s : students)
        {
            studentsDTO.add(toResponseDTO(s));
        }
        return studentsDTO;
    }

    public Optional<StudentDTO> getById(int id) { return repository.findById(id)
            .map(this::toResponseDTO);

    }

    public List<StudentDTO> getByName(String name) {
        List<Student> students = repository.findByName(name);
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s: students) {
            studentsDTO.add(toResponseDTO(s));
        }
        return studentsDTO;
    }

    public List<StudentDTO> getByEmail(String email) {
        List<Student> students = repository.findByEmail(email);
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s: students)
        {
            studentsDTO.add(toResponseDTO(s));
        }
        return studentsDTO;
    }

    private StudentDTO toResponseDTO(Student student){
        return new StudentDTO(student.getStudentName(), student.getStudentAge(), student.getStudentEmail());
    }

    private Student toEntity(StudentDTO request){
        Student student = new Student();

        if(!request.getStudentName().isBlank()){
            student.setStudentName(request.getStudentName());
        }
        if(request.getStudentAge() != 0){
            student.setStudentAge(request.getStudentAge());
        }
        if(!request.getStudentEmail().isBlank()){
            student.setStudentEmail(request.getStudentEmail());
        }
        return student;
    }

}
