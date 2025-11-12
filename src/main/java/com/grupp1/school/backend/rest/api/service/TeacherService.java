package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.exception.ResourceAlreadyExistsException;
import com.grupp1.school.backend.rest.api.exception.ResourceNotFoundException;
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

    public TeacherDTO getById(int id){
        return repository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find teacher with id " + id));
    }

    public TeacherDTO getByEmail(String email){
        return repository.findByEmail(email).map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find teacher with email " + email));
    }

    public List<TeacherDTO> getByName(String name){
        return repository.findByName(name).stream().map(this::toDTO).toList();
    }

    public TeacherDTO addTeacher(TeacherDTO dto){
        if(repository.existsById(dto.getId())) throw new ResourceAlreadyExistsException("This teacher already exists");
        return  toDTO(repository.save(toEntity(dto)));
    }

    public boolean deleteById(int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public TeacherDTO update(TeacherDTO dto){

        Optional<Teacher> opt = repository.findById(dto.getId());

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

    private Teacher toEntity(TeacherDTO dto){
        return new Teacher(dto.getId(), dto.getAge(), dto.getName(), dto.getEmail());
    }


    private TeacherDTO toDTO(Teacher teacher){
        return new TeacherDTO(teacher.getId(), teacher.getAge(), teacher.getName(), teacher.getEmail());
    }
}
