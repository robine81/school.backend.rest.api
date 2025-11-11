package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.model.Student;
import com.grupp1.school.backend.rest.api.model.dto.StudentDTO;
import com.grupp1.school.backend.rest.api.repository.StudentRepository;
import com.grupp1.school.backend.rest.api.repository.StudentRepositoryOld;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) { this.repository = repository; }

    public List<StudentDTO> getAll() {
        List<Student> students = repository.findAll();
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s : students)
        {
            studentsDTO.add(toDTO(s));
        }
        return studentsDTO;
    }

    public Optional<StudentDTO> getById(Integer id) {
        return repository.findById(id)
            .map(this::toDTO);
    }

    public List<StudentDTO> getByName(String name) {
        List<Student> students = repository.findByStudentName(name);
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s: students) {
            studentsDTO.add(toDTO(s));
        }
        return studentsDTO;
    }

    public List<StudentDTO> getByEmail(String email) {
        List<Student> students = repository.findByStudentEmail(email);
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s: students)
        {
            studentsDTO.add(toDTO(s));
        }
        return studentsDTO;
    }

    public List<StudentDTO> getByAge(int age) {
        List<Student> students = repository.findByStudentAge(age);
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s: students)
        {
            studentsDTO.add(toDTO(s));
        }
        return studentsDTO;
    }

    public StudentDTO addStudent (StudentDTO request) {
        return toDTO(repository.save(toEntity(request)));
    }

    public StudentDTO updateStudent (int id, StudentDTO request) {
        Optional<Student> existing = repository.findById(id);

        if(existing.isPresent()){
            existing.get().setStudentId(request.getStudentId());
            existing.get().setStudentName(request.getStudentName());
            existing.get().setStudentEmail(request.getStudentEmail());
            existing.get().setStudentAge(request.getStudentAge());
            return toDTO(existing.get());
        }
        return null;
    }

    public boolean deleteStudent(int id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean emailExists(String email) {
        return repository.existsByStudentEmail(email);
    }

    private StudentDTO toDTO(Student student){
        return new StudentDTO(student.getStudentId(), student.getStudentName(), student.getStudentEmail(), student.getStudentAge());
    }

    private Student toEntity(StudentDTO dto){
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
}
