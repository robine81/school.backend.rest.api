package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.exception.ResourceAlreadyExistsException;
import com.grupp1.school.backend.rest.api.exception.ResourceNotFoundException;
import com.grupp1.school.backend.rest.api.model.Course;
import com.grupp1.school.backend.rest.api.model.Enrolment;
import com.grupp1.school.backend.rest.api.model.Student;
import com.grupp1.school.backend.rest.api.model.dto.EnrolmentResponseDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentCourseResponseDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentResponseDTO;
import com.grupp1.school.backend.rest.api.repository.CourseRepository;
import com.grupp1.school.backend.rest.api.repository.StudentRepository;
import com.grupp1.school.backend.rest.api.service.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.grupp1.school.backend.rest.api.service.mapper.StudentMapper.toEntity;
import static com.grupp1.school.backend.rest.api.service.mapper.StudentMapper.toResponseDTO;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository repository, CourseRepository courseRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    public List<StudentResponseDTO> getAll() {
        List<Student> students = repository.findAll();
        List<StudentResponseDTO> studentsRespDTO = new ArrayList<>();

        for(Student s : students)
        {
            studentsRespDTO.add(toResponseDTO(s));
        }
        return studentsRespDTO;
    }

    public StudentResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(StudentMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    public long getTotalStudentCount() {
        return repository.count();
    }

    //@Query method
    public List<StudentResponseDTO> searchByName(String name) {
        List<Student> students = repository.searchByName(name);
        List<StudentResponseDTO> studentsDTO = new ArrayList<>();

        for(Student s : students) {
            studentsDTO.add(toResponseDTO(s));
        }
        return studentsDTO;
    }

    //Spring Boot method
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
        List<Student> students = repository.findByStudentNameContainingIgnoreCaseAndStudentAge(name, age);
        List<StudentResponseDTO> studentsDTO = new ArrayList<>();

        for(Student s: students)
        {
            studentsDTO.add(toResponseDTO(s));
        }
        return studentsDTO;
    }

    public List<StudentResponseDTO> searchByNameAndAge(String name, int age) {
        List<Student> students = repository.searchByNameAndAge(name, age);
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

    public List<StudentResponseDTO> searchByAge(int age) {
        List<Student> students = repository.searchByAge(age);
        List<StudentResponseDTO> studentsRespDTO = new ArrayList<>();

        for(Student s: students)
        {
            studentsRespDTO.add(toResponseDTO(s));
        }
        return studentsRespDTO;
    }

    public List<StudentResponseDTO> searchByAgeBetween(int min, int max){
        if(min > max) {
            throw new IllegalArgumentException("Min age cannot be bigger than max age");
        }
        return repository.searchByAgeBetween(min, max)
                .stream()
                .map(StudentMapper::toResponseDTO)
                .toList();
    }

    public List<StudentResponseDTO> findByAgeBetween(int min, int max){
        if(min > max) {
            throw new IllegalArgumentException("Min age cannot be bigger than max age");
        }
        return repository.findByStudentAgeBetween(min, max)
                .stream()
                .map(StudentMapper::toResponseDTO)
                .toList();
    }

    public StudentResponseDTO create(StudentRequestDTO request) {
        if(repository.existsByStudentEmail(request.getStudentEmail())) {
            throw new ResourceAlreadyExistsException("Email " + request.getStudentEmail() + " is already registered");
        }
        return toResponseDTO(repository.save(toEntity(request)));
    }

    public Optional<StudentResponseDTO> update(Long id, StudentRequestDTO request) {
        return repository.findById(id)
                .map(student -> {
                    if(!student.getStudentEmail().equals(request.getStudentEmail())
                            && repository.existsByStudentEmail(request.getStudentEmail())) {
                        throw new ResourceAlreadyExistsException("Email " + request.getStudentEmail() + " is already registered");
                    }
                    student.setStudentName(request.getStudentName());
                    student.setStudentEmail(request.getStudentEmail());
                    student.setStudentAge(request.getStudentAge());
                    return toResponseDTO(repository.save(student));
                });
    }

    public boolean delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean emailExists(String email) {
        return repository.existsByStudentEmail(email);
    }

    public List<EnrolmentResponseDTO> findEnrolmentsByStudentId(Long studentId) {
        Student existing = repository.findById(studentId)
        .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + studentId + " not found"));
        return existing.getEnrolments()
                .stream()
                .map(e -> {
                    EnrolmentResponseDTO dto = new EnrolmentResponseDTO();
                    dto.setId(e.getId());
                    dto.setCourseId(e.getCourse().getId());
                    dto.setStudentId(e.getStudent().getStudentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public StudentCourseResponseDTO findByIdAndGetCoursesWithGrades(Long id){
        Student existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found"));
        return StudentMapper.toCourseResponseDTO(existing);
    }

    public StudentCourseResponseDTO setGradeForEnrolmentToCourse(Long id, Long courseId, Integer grade){
        Student existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found"));
        Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course with ID " + courseId + " not found."));
        Optional<Enrolment> enrolment = existing.getEnrolments().stream().filter(e -> e.getCourse().getId().equals(courseId)).findFirst();
        if (enrolment.isPresent()) {
            enrolment.get().setGrade(grade);
        } else {
            existing.getEnrolments().add(new Enrolment(existing, existingCourse, grade));
        }
        return StudentMapper.toCourseResponseDTO(repository.save(existing));
    }
}
