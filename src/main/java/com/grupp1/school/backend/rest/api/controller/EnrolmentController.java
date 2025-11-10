package com.grupp1.school.backend.rest.api.controller;

import com.grupp1.school.backend.rest.api.service.EnrolmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrolment")
public class EnrolmentController {
    @Autowired
    private EnrolmentService enrolmentService;


}
