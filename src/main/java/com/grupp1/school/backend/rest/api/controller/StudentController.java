package com.grupp1.school.backend.rest.api.controller;

import com.grupp1.school.backend.rest.api.model.dto.StudentDTO;
import com.grupp1.school.backend.rest.api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getALl() { return ResponseEntity.ok(service.getAll()); }

    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getById(@Valid @PathVariable int id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO){
        if(service.emailExists(studentDTO.getStudentEmail())){
            return ResponseEntity.status(409).build();
        }
       return ResponseEntity.status(201).body(service.addStudent(studentDTO));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable int id, @Valid @RequestBody StudentDTO studentDTO){
        StudentDTO result = service.updateStudent(id, studentDTO);
        if(result != null){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        boolean removed = service.deleteStudent(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> search(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        List<StudentDTO> results;

        if(age != null){
            results = service.getByAge(age);
        } else if (name != null) {
            results = service.getByName(name);
        } else {
            results= service.getAll();
        }
        return ResponseEntity.ok(results);
    }
}
