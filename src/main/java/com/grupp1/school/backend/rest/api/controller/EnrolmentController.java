package com.grupp1.school.backend.rest.api.controller;

import com.grupp1.school.backend.rest.api.model.dto.EnrolmentDTO;
import com.grupp1.school.backend.rest.api.service.EnrolmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrolment")
public class EnrolmentController {
    @Autowired
    private EnrolmentService enrolmentService;

    @PostMapping("/create")
    public ResponseEntity<EnrolmentDTO> create(@Valid @RequestBody EnrolmentDTO enrolment) {
        return ResponseEntity.ok(enrolmentService.createEnrolment(enrolment));
    }
}
