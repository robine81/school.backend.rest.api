package com.grupp1.school.backend.rest.api.controller;

import com.grupp1.school.backend.rest.api.model.Teacher;
import com.grupp1.school.backend.rest.api.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService service;

    public TeacherController(TeacherService service){
        this.service = service;
    }

    @GetMapping
    public List<Teacher> getAll(){
        return service.getAll();
    }
}
