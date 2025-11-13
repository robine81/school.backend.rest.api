package com.grupp1.school.backend.rest.api.controller;

import com.grupp1.school.backend.rest.api.model.dto.CourseRequestDTO;
import com.grupp1.school.backend.rest.api.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    CourseService service;

    public CourseController(CourseService service){
        this.service = service;
    }

    @GetMapping
    public List<?> getAllCourses(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@Min(value = 1, message = "ID needs to be non-zero positive integer.") @PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<?>> search(@RequestParam(required = false) String name){
        if (name != null){
            return ResponseEntity.ok(service.getByName(name));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@Valid @RequestBody CourseRequestDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<?> updateCourse(@Valid @RequestBody CourseRequestDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@Min(value = 1, message = "ID needs to be non-zero positive integer.") @PathVariable Long id){
        if (service.deleteById(id)){
            return ResponseEntity.ok("Deletion successful.");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/enrolled_students")
    public ResponseEntity<?> getEnrolledStudentsById(@PathVariable Long id){
        return ResponseEntity.ok(service.getEnrolledStudentsById(id));
    }
}
