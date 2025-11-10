package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.model.Teacher;
import com.grupp1.school.backend.rest.api.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository repository;

    public TeacherService (TeacherRepository repository) {
        this.repository = repository;
    }

    public List<Teacher> getAll(){
        return repository.getAll();
    }

    public Optional<Teacher> getById(int id){
        return repository.getById(id);
    }

    public Optional<Teacher> getByEmail(String email){
        return repository.getbyEmail(email);
    }

    public List<Teacher> getByName(String name){
        return repository.getByName(name);
    }

}
