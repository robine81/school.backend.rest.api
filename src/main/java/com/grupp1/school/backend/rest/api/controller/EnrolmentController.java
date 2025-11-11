package com.grupp1.school.backend.rest.api.controller;

import com.grupp1.school.backend.rest.api.model.Enrolment;
import com.grupp1.school.backend.rest.api.model.dto.EnrolmentDTO;
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
    public ResponseEntity<EnrolmentDTO> create(@Valid @RequestBody EnrolmentDTO enrolment) {
        return ResponseEntity.ok(enrolmentService.createEnrolment(enrolment));
    }

    @GetMapping("/listall")
    public ResponseEntity<List<Enrolment>> listAll() {
        return ResponseEntity.ok(enrolmentService.listEnrolments());
    }
}
