package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.model.Teacher;
import com.grupp1.school.backend.rest.api.model.dto.TeacherDTO;
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
        return repository.findAll();
    }

    public Optional<Teacher> getById(int id){
        return repository.findById(id);
    }

    public Optional<Teacher> getByEmail(String email){
        return repository.findByEmail(email);
    }

    public List<Teacher> getByName(String name){
        return repository.findByName(name);
    }

    public TeacherDTO addTeacher(TeacherDTO teacher){
        return  toDTO(repository.save(toEntity(teacher)));
    }

    private Teacher toEntity(TeacherDTO dto){
        return new Teacher(dto.getId(), dto.getAge(), dto.getName(), dto.getEmail());
    }


    private TeacherDTO toDTO(Teacher teacher){
        return new TeacherDTO(teacher.getId(), teacher.getAge(), teacher.getName(), teacher.getEmail());
    }
}
