package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.model.Course;
import com.grupp1.school.backend.rest.api.model.dto.CourseDTO;
import com.grupp1.school.backend.rest.api.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository){
        this.repository = repository;
    }

    public CourseDTO create(CourseDTO dto){
        Course entity = mapDtoToEntity(dto);
        repository.save(entity);
        return dto;
    }

    public List<CourseDTO> getAll(){
        return repository.findAll().stream().map(this::mapEntityToDto).toList();
    }

    public Optional<CourseDTO> getById(Integer id){
        return repository.findById(id).map(this::mapEntityToDto);
    }

    public List<CourseDTO> getByTeacherId(Integer teacherId){
        return repository.findByTeacherId(teacherId).stream().map(this::mapEntityToDto).toList();
    }

    public CourseDTO update(CourseDTO dto){
        Course entity = repository.update(dto.getId(), dto.getName(), dto.getTeacherId(), dto.getMaxStudents());
        return mapEntityToDto(entity);
    }

    private Course mapDtoToEntity(CourseDTO dto){
        return new Course(dto.getId(), dto.getName(), dto.getTeacherId(), dto.getMaxStudents());
    }

    private CourseDTO mapEntityToDto(Course entity){
        return new CourseDTO(entity.getId(), entity.getName(), entity.getTeacherId(), entity.getMaxStudents());
    }
}
