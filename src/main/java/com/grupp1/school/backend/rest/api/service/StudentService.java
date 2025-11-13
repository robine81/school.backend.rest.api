package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.exception.ResourceNotFoundException;
import com.grupp1.school.backend.rest.api.model.Student;
import com.grupp1.school.backend.rest.api.model.dto.EnrolmentResponseDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentResponseDTO;
import com.grupp1.school.backend.rest.api.repository.StudentRepository;
import com.grupp1.school.backend.rest.api.service.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.grupp1.school.backend.rest.api.service.mapper.StudentMapper.toEntity;
import static com.grupp1.school.backend.rest.api.service.mapper.StudentMapper.toResponseDTO;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) { this.repository = repository; }

    public List<StudentResponseDTO> getAll() {
        List<Student> students = repository.findAll();
        List<StudentResponseDTO> studentsRespDTO = new ArrayList<>();

        for(Student s : students)
        {
            studentsRespDTO.add(toResponseDTO(s));
        }
        return studentsRespDTO;
    }

    public StudentResponseDTO getById(Integer id) {
        return repository.findById(id)
            .map(StudentMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    public List<StudentResponseDTO> findByName(String name) {
        List<Student> students = repository.findByStudentNameContainingIgnoreCase(name);
        List<StudentResponseDTO> studentsDTO = new ArrayList<>();

        for(Student s: students) {
            studentsDTO.add(toResponseDTO(s));
        }
        return studentsDTO;
    }

    public List<StudentResponseDTO> findByEmail(String email) {
        List<Student> students = repository.findByStudentEmail(email);
        List<StudentResponseDTO> studentsDTO = new ArrayList<>();

        for(Student s: students)
        {
            studentsDTO.add(toResponseDTO(s));
        }
        return studentsDTO;
    }

    public List<StudentResponseDTO> findByNameAndAge(String name, int age) {
        List<Student> students = repository. findByStudentNameContainingIgnoreCaseAndStudentAge(name, age);
        List<StudentResponseDTO> studentsDTO = new ArrayList<>();

        for(Student s: students)
        {
            studentsDTO.add(toResponseDTO(s));
        }
        return studentsDTO;
    }

    public List<StudentResponseDTO> findByAge(int age) {
        List<Student> students = repository.findByStudentAge(age);
        List<StudentResponseDTO> studentsRespDTO = new ArrayList<>();

        for(Student s: students)
        {
            studentsRespDTO.add(toResponseDTO(s));
        }
        return studentsRespDTO;
    }

    public List<StudentResponseDTO> findByAgeBetween(int min, int max){
        return repository.findByStudentAgeBetween(min, max)
                .stream()
                .map(StudentMapper::toResponseDTO)
                .toList();
    }

    public StudentResponseDTO create(StudentRequestDTO request) {
        return toResponseDTO(repository.save(toEntity(request)));
    }

    public Optional<StudentResponseDTO> update(int id, StudentRequestDTO request) {
        return repository.findById(id)
                .map(student -> {
                    student.setStudentName(request.getStudentName());
                    student.setStudentEmail(request.getStudentEmail());
                    student.setStudentAge(request.getStudentAge());
                    return toResponseDTO(repository.save(student));
                });
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

    /*static public StudentResponseDTO toResponseDTO(Student student){
        return new StudentResponseDTO(student.getStudentId(), student.getStudentName(), student.getStudentEmail(), student.getStudentAge());
    }*/

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
