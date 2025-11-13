package com.grupp1.school.backend.rest.api.controller;

import com.grupp1.school.backend.rest.api.model.dto.EnrolmentResponseDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentDTO;
import com.grupp1.school.backend.rest.api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getALl() { return ResponseEntity.ok(service.getAll()); }

    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getById(@Valid @PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/email")
    public ResponseEntity<List<StudentDTO>> getByEmail(@Valid @RequestParam String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @GetMapping("/name_age")
    public ResponseEntity<List<StudentDTO>> getByNameAndAge(@RequestParam String name, @RequestParam int age) {
        return ResponseEntity.ok(service.findByNameAndAge(name, age));
    }

    @GetMapping("/age")
    public ResponseEntity<List<StudentDTO>> getByAge(@Valid @RequestParam int age) {
        return ResponseEntity.ok(service.findByAge(age));
    }

    @GetMapping("/age_range")
    public ResponseEntity<List<StudentDTO>> getByAgeBetween(@RequestParam int min, @RequestParam int max){
        return ResponseEntity.ok(service.findByAgeBetween(min, max));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO){
        if(service.emailExists(studentDTO.getStudentEmail())){
            return ResponseEntity.status(409).build();
        }
       return ResponseEntity.status(201).body(service.create(studentDTO));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable int id, @Valid @RequestBody StudentDTO studentDTO){
        StudentDTO result = service.update(id, studentDTO);
        if(result != null){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        boolean removed = service.delete(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/find")
    public ResponseEntity<List<StudentDTO>> find(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        List<StudentDTO> results;

        if(name != null && age != null){
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

    @GetMapping("/enrolments/{id}")
    public List<EnrolmentResponseDTO> listEnrolments (@PathVariable int id) {
        return service.findEnrolmentsByStudentId(id);
    }
}
