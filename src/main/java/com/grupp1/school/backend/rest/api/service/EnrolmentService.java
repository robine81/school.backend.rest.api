package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.exception.ResourceAlreadyExistsException;
import com.grupp1.school.backend.rest.api.model.Enrolment;
import com.grupp1.school.backend.rest.api.model.dto.EnrolmentDTO;
import com.grupp1.school.backend.rest.api.repository.EnrolmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolmentService {
    @Autowired
    EnrolmentRepository enrolmentRepository;

    public List<Enrolment> listEnrolments() {
        return enrolmentRepository.listEnrolments();
    }

    public EnrolmentDTO createEnrolment(EnrolmentDTO enrolmentDTO) {
        if (enrolmentRepository.existsByStudentIDAndCourseID(enrolmentDTO.getStudentID(), enrolmentDTO.getCourseId())) {
            throw new ResourceAlreadyExistsException("Resource Already Exists");
        }
        Enrolment e = convertToEntity(enrolmentDTO);
        Enrolment created = enrolmentRepository.save(e);
        return convertToDTO(created);
    }

    Enrolment convertToEntity(EnrolmentDTO enrolmentDTO) {
        Enrolment e = new Enrolment(enrolmentDTO.getId(), enrolmentDTO.getStudentID(), enrolmentDTO.getCourseId());
        return e;
    }

    EnrolmentDTO convertToDTO(Enrolment enrolment) {
        EnrolmentDTO dto = new EnrolmentDTO(enrolment.getId(), enrolment.getStudentID(), enrolment.getCourseId());
        return dto;
    }

}
