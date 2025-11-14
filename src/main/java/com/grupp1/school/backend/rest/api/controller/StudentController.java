package com.grupp1.school.backend.rest.api.controller;

import com.grupp1.school.backend.rest.api.model.dto.EnrolmentResponseDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentResponseDTO;
import com.grupp1.school.backend.rest.api.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<StudentResponseDTO> getById(@Min(value = 1, message = "ID needs to be non-zero positive integer.") @PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<StudentResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO studentRequestDTO){
        return service.update(id, studentRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Min(value = 1, message = "ID needs to be non-zero positive integer.") @PathVariable Long id){
        boolean removed = service.delete(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    //using Spring Data-method
    @GetMapping("/find")
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

    //using @Query method
    @GetMapping ("/search")
    public ResponseEntity<List<StudentResponseDTO>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer min,
            @RequestParam(required = false) Integer max) {
        List<StudentResponseDTO> results;

        if(min != null && max != null) {
            results = service.searchByAgeBetween(min, max);
        } else if(name != null && age != null){
            results = service.searchByNameAndAge(name, age);
        } else if(age != null){
            results = service.searchByAge(age);
        } else if (name != null) {
            results = service.searchByName(name);
        } else {
            results= service.getAll();
        }
        return ResponseEntity.ok(results);

    }

    @GetMapping("/statistics/count")
    public ResponseEntity<Map<String, Long>> getStudentCount() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalStudents", service.getTotalStudentCount());
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/{id}/enrolments")
    public ResponseEntity<List<EnrolmentResponseDTO>> listStudentsEnrolments(@PathVariable Long id) {
        return ResponseEntity.ok(service.findEnrolmentsByStudentId(id));
    }

    @GetMapping("/{id}/grades")
    public ResponseEntity<?> getStudentAndCoursesWithGrades(@PathVariable Long id){
        return ResponseEntity.ok(service.findByIdAndGetCoursesWithGrades(id));
    }

    @PatchMapping("{id}/grades")
    public ResponseEntity<?> setGradeForStudentInCourse(@PathVariable Long id,
                                                        @RequestParam Long courseId,
                                                        @RequestParam @Positive @Max(100) Integer grade){
        return ResponseEntity.ok(service.setGradeForEnrolmentToCourse(id, courseId, grade));
    }
}
