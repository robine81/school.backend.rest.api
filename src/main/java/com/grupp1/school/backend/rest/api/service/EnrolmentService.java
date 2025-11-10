package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.model.Enrolment;
import com.grupp1.school.backend.rest.api.repository.EnrolmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrolmentService {
    @Autowired
    EnrolmentRepository enrolmentRepository;

    public EnrolmentDTO createEnrolment(EnrolmentDTO enrolmentDto) {

    }

}
