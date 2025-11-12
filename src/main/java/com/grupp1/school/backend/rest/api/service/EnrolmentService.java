package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.exception.ResourceAlreadyExistsException;
import com.grupp1.school.backend.rest.api.model.Enrolment;
import com.grupp1.school.backend.rest.api.model.dto.EnrolmentDTO;
import com.grupp1.school.backend.rest.api.repository.EnrolmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrolmentService {
    @Autowired
    EnrolmentRepository enrolmentRepository;

    public List<EnrolmentDTO> listEnrolments() {
        return enrolmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EnrolmentDTO createEnrolment(EnrolmentDTO enrolmentDTO) {
        if (enrolmentRepository.existsByStudentIDAndCourseID(enrolmentDTO.getStudentID(), enrolmentDTO.getCourseID())) {
            throw new ResourceAlreadyExistsException("Resource Already Exists");
        }
        Enrolment e = convertToEntity(enrolmentDTO);
        Enrolment created = enrolmentRepository.save(e);
        return convertToDTO(created);
    }

    Enrolment convertToEntity(EnrolmentDTO enrolmentDTO) {
        return new Enrolment(enrolmentDTO.getId(), enrolmentDTO.getStudentID(), enrolmentDTO.getCourseID());
    }

    EnrolmentDTO convertToDTO(Enrolment enrolment) {
        return new EnrolmentDTO(enrolment.getId(), enrolment.getStudentID(), enrolment.getCourseID());
    }

}

