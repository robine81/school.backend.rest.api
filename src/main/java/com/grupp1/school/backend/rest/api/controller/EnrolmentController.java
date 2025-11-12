package com.grupp1.school.backend.rest.api.controller;

import com.grupp1.school.backend.rest.api.model.dto.EnrolmentRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.EnrolmentResponseDTO;
import com.grupp1.school.backend.rest.api.service.EnrolmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrolment")
public class EnrolmentController {
    @Autowired
    private EnrolmentService enrolmentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody EnrolmentRequestDTO enrolment) {
        return ResponseEntity.ok(enrolmentService.createEnrolment(enrolment));
    }

    @GetMapping("/")
    public ResponseEntity<List<?>> listAll() {
        return ResponseEntity.ok(enrolmentService.listEnrolments());
    }
}
