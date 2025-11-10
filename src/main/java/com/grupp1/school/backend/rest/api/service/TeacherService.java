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

    private Teacher toEntity(TeacherDTO request){
        Teacher teacher = new Teacher();

        if (request.getName() != null){
            teacher.setName(request.getName());
        }
        if(request.getEmail() != null){
            teacher.setEmail(request.getEmail());
        }
        if(request.getAge() != null){
            teacher.setAge(request.getAge());
        }
        return teacher;
    }

    private void addTeacher(TeacherDTO teacher){

    }

    private TeacherDTO toTeacherDTO(Teacher teacher){
        return new TeacherDTO(teacher.getId(), teacher.getName(), teacher.getEmail());
    }
}
