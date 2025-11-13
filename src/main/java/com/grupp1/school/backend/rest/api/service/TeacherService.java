package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.exception.ResourceAlreadyExistsException;
import com.grupp1.school.backend.rest.api.exception.ResourceNotFoundException;
import com.grupp1.school.backend.rest.api.model.Teacher;
import com.grupp1.school.backend.rest.api.model.dto.TeacherRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.TeacherResponseDTO;
import com.grupp1.school.backend.rest.api.repository.TeacherRepository;
import com.grupp1.school.backend.rest.api.service.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository repository;

    public TeacherService (TeacherRepository repository) {
        this.repository = repository;
    }

    public List<TeacherResponseDTO> getAll(){
        return repository.findAll()
                .stream().map(TeacherMapper::MapEntityToResponse)
                .toList();
    }

    public TeacherResponseDTO getById(Long id){
        return repository.findById(id).map(TeacherMapper::MapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find teacher with id " + id));
    }

    public TeacherResponseDTO getByEmail(String email){
        return repository.findByEmail(email).map(TeacherMapper::MapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find teacher with email " + email));
    }

    public List<TeacherResponseDTO> getByName(String name){
        return repository.findByName(name).stream().map(TeacherMapper::MapEntityToResponse).toList();
    }

    //TODO refactor this method
    public TeacherResponseDTO addTeacher(TeacherRequestDTO dto){
        if(repository.existsByEmail(dto.getEmail())) {
            throw new ResourceAlreadyExistsException("This teacher already exists");
        } else {
            Teacher saved = repository.save(TeacherMapper.MapRequestToEntity(dto));
            return  TeacherMapper.MapEntityToResponse(saved);
        }
    }

    public boolean deleteById(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public TeacherResponseDTO update(TeacherRequestDTO dto){

        Optional<Teacher> opt = repository.findById(dto.getId());

        if (opt.isPresent()){
            Teacher existing = opt.get();
            existing.setName(dto.getName());
            existing.setEmail(dto.getEmail());
            repository.save(existing);
            return TeacherMapper.MapEntityToResponse(existing);
        }

        return null;
    }


}
