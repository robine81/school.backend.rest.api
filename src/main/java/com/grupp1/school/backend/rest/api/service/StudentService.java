package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }
}
