package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.exception.ResourceNotFoundException;
import com.grupp1.school.backend.rest.api.model.Student;
import com.grupp1.school.backend.rest.api.model.dto.EnrolmentResponseDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentDTO;
import com.grupp1.school.backend.rest.api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public StudentDTO getById(Integer id) {
        return repository.findById(id)
            .map(StudentService::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    public List<StudentDTO> findByName(String name) {
        List<Student> students = repository.findByStudentName(name);
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s: students) {
            studentsDTO.add(toDTO(s));
        }
        return studentsDTO;
    }

    public List<StudentDTO> findByEmail(String email) {
        List<Student> students = repository.findByStudentEmail(email);
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s: students)
        {
            studentsDTO.add(toDTO(s));
        }
        return studentsDTO;
    }

    public List<StudentDTO> findByNameAndAge(String name, int age) {
        List<Student> students = repository. findByStudentNameContainingIgnoreCaseAndStudentAge(name, age);
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s: students)
        {
            studentsDTO.add(toDTO(s));
        }
        return studentsDTO;
    }

    public List<StudentDTO> findByAge(int age) {
        List<Student> students = repository.findByStudentAge(age);
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s: students)
        {
            studentsDTO.add(toDTO(s));
        }
        return studentsDTO;
    }

    public List<StudentDTO> findByAgeBetween(int min, int max){
        return repository.findByStudentAgeBetween(min, max)
                .stream()
                .map(StudentService::toDTO)
                .toList();
    }


    public StudentDTO create(StudentDTO request) {
        return toDTO(repository.save(toEntity(request)));

    }

    public StudentDTO update(int id, StudentDTO request) {
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

    public boolean delete(int id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean emailExists(String email) {
        return repository.existsByStudentEmail(email);
    }

    static public StudentDTO toDTO(Student student){
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

    public List<EnrolmentResponseDTO> findEnrolmentsByStudentId(Integer studentId) {
        Optional<Student> existing = repository.findById(studentId);
        if(existing.isPresent()){
            return existing.get().getEnrolments()
                    .stream()
                    .map(e -> {
                        EnrolmentResponseDTO dto = new EnrolmentResponseDTO();
                        dto.setId(Long.valueOf(e.getId()));
                        dto.setCourseId(Long.valueOf(e.getCourse().getId().intValue()));
                        dto.setStudentId(Long.valueOf(e.getStudent().getStudentId().intValue()));
                        return dto;
                    })
                    .collect(Collectors.toList());
        }
        else {
            return List.of();
        }
    }
}
