package com.grupp1.school.backend.rest.api.controller;

import com.grupp1.school.backend.rest.api.model.dto.TeacherDTO;
import com.grupp1.school.backend.rest.api.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService service;

    public TeacherController(TeacherService service){
        this.service = service;
    }

    @GetMapping
    public List<TeacherDTO> getAll(){
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> addTeacher(@Valid @RequestBody TeacherDTO dto){
        return ResponseEntity.ok(service.addTeacher(dto));
    }
}
