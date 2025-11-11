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

    public List<TeacherDTO> getAll(){
        return repository.findAll()
                .stream().map(this::toDTO)
                .toList();
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

    public boolean deleteById(int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public TeacherDTO updateTeacher(int id, TeacherDTO dto){

        Optional<Teacher> opt = repository.findById(id);

        if (opt.isPresent()){
            Teacher existing = opt.get();
            existing.setName(dto.getName());
            existing.setEmail(dto.getEmail());
            existing.setAge(dto.getAge());
            repository.save(existing);
            return toDTO(existing);
        }

        return null;
    }

    public TeacherDTO patchTeacher(int id, TeacherDTO dto){
        Optional<Teacher> opt = repository.findById(id);

        if (opt.isPresent()) {
            Teacher existing = opt.get();
            if(dto.getName() != null){
                existing.setName(dto.getName());
            }
            if(dto.getAge() != null){
                existing.setAge(dto.getAge());
            }
            if(dto.getEmail() != null){
                existing.setEmail(dto.getEmail());
            }
            repository.save(existing);
            return toDTO(existing);
        }
        return null;
    }

    private Teacher toEntity(TeacherDTO dto){
        return new Teacher(dto.getId(), dto.getAge(), dto.getName(), dto.getEmail());
    }


    private TeacherDTO toDTO(Teacher teacher){
        return new TeacherDTO(teacher.getId(), teacher.getAge(), teacher.getName(), teacher.getEmail());
    }
}
