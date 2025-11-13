package com.grupp1.school.backend.rest.api.controller;

import com.grupp1.school.backend.rest.api.model.dto.EnrolmentResponseDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentResponseDTO;
import com.grupp1.school.backend.rest.api.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(@Valid @RequestBody StudentRequestDTO studentDTO){
        if(service.emailExists(studentDTO.getStudentEmail())){
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(201).body(service.create(studentDTO));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAll() { return ResponseEntity.ok(service.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@Min(value = 1, message = "ID needs to be non-zero positive integer.") @PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<StudentResponseDTO> update(
            @PathVariable int id,
            @Valid @RequestBody StudentRequestDTO studentRequestDTO){
        return service.update(id, studentRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Min(value = 1, message = "ID needs to be non-zero positive integer.") @PathVariable int id){
        boolean removed = service.delete(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDTO>> find(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge){
        List<StudentResponseDTO> results;

        if(minAge != null && maxAge != null) {
            results = service.findByAgeBetween(minAge, maxAge);
        } else if(name != null && age != null){
            results = service.findByNameAndAge(name, age);
        } else if(age != null){
            results = service.findByAge(age);
        } else if (name != null) {
            results = service.findByName(name);
        } else {
            results= service.getAll();
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}/enrolments")
    public ResponseEntity<List<EnrolmentResponseDTO>> listStudentsEnrolments(@PathVariable int id) {
        return ResponseEntity.ok(service.findEnrolmentsByStudentId(id));
    }

    @GetMapping("/enrolments/{id}")
    public List<EnrolmentResponseDTO> listEnrolments (@PathVariable int id) {
        return service.findEnrolmentsByStudentId(id);
    }
}
