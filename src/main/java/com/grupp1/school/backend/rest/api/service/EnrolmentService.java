package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.exception.ResourceAlreadyExistsException;
import com.grupp1.school.backend.rest.api.exception.ResourceNotFoundException;
import com.grupp1.school.backend.rest.api.model.Course;
import com.grupp1.school.backend.rest.api.model.Enrolment;
import com.grupp1.school.backend.rest.api.model.Student;
import com.grupp1.school.backend.rest.api.model.dto.EnrolmentRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.EnrolmentResponseDTO;
import com.grupp1.school.backend.rest.api.repository.CourseRepository;
import com.grupp1.school.backend.rest.api.repository.EnrolmentRepository;
import com.grupp1.school.backend.rest.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrolmentService {
    @Autowired
    EnrolmentRepository enrolmentRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    public List<EnrolmentResponseDTO> listEnrolments() {
        return enrolmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EnrolmentResponseDTO createEnrolment(EnrolmentRequestDTO enrolmentRequestDTO) {

        if (enrolmentRepository.existsByStudentStudentIdAndCourseId(Integer.valueOf(enrolmentRequestDTO.getStudentID().intValue()), Integer.valueOf(enrolmentRequestDTO.getCourseID().intValue()))) {
            throw new ResourceAlreadyExistsException("Resource Already Exists");
        }
        Enrolment e = convertToEntity(enrolmentRequestDTO);
        Enrolment created = enrolmentRepository.save(e);
        return convertToDTO(created);
    }

    Enrolment convertToEntity(EnrolmentRequestDTO enrolmentRequestDTO) {
        Enrolment e = new Enrolment();
        e.setId(enrolmentRequestDTO.getId());
        Optional<Student> student = studentRepository.findById(Integer.valueOf(enrolmentRequestDTO.getStudentID().intValue()));
        if (!student.isPresent()) {
            throw new ResourceNotFoundException("Student not found");
        }
        e.setStudent(student.get());
        Optional<Course> course = courseRepository.findById(Integer.valueOf(enrolmentRequestDTO.getCourseID().intValue()));
        if (!course.isPresent()) {
            throw new ResourceNotFoundException("Course not found");
        }
        e.setCourse(course.get());
        return e;
    }

    EnrolmentResponseDTO convertToDTO(Enrolment enrolment) {
        EnrolmentResponseDTO e = new EnrolmentResponseDTO();
        e.setId(enrolment.getId());
        e.setCourseId(Long.valueOf(enrolment.getCourse().getId()));
        e.setStudentId(Long.valueOf(enrolment.getStudent().getStudentId()));
        return e;
    }

}
