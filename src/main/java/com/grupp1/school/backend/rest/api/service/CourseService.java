package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.exception.ResourceAlreadyExistsException;
import com.grupp1.school.backend.rest.api.exception.ResourceNotFoundException;
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
        if (repository.existsById(dto.getId())) throw new ResourceAlreadyExistsException("Course already exists.");
        return mapEntityToDto(repository.save(entity));
    }

    public List<CourseDTO> getAll(){
        return repository.findAll().stream().map(this::mapEntityToDto).toList();
    }

    public CourseDTO getById(Integer id){
        return repository.findById(id).map(this::mapEntityToDto).orElseThrow(() -> new ResourceNotFoundException("Couldn't find course with id = " + id));
    }

    public List<CourseDTO> getByTeacherId(Integer teacherId){
        return repository.findByTeacherId(teacherId).stream().map(this::mapEntityToDto).toList();
    }

    public List<CourseDTO> getByName(String name){
        return repository.findByName(name).stream().map(this::mapEntityToDto).toList();
    }

    public CourseDTO update(CourseDTO dto){
        Course existing = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Couldn't find course with id = " + dto.getId()));
        if(dto.getName() != null){
            existing.setName(dto.getName());
        }
        if(dto.getTeacherId() != null){
            existing.setTeacherId(dto.getTeacherId());
        }
        if(dto.getMaxStudents() != null){
            existing.setMaxStudents(dto.getMaxStudents());
        }
        repository.save(existing);

        return mapEntityToDto(existing);
    }

    public boolean deleteById(Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private Course mapDtoToEntity(CourseDTO dto){
        return new Course(dto.getId(), dto.getName(), dto.getTeacherId(), dto.getMaxStudents());
    }

    private CourseDTO mapEntityToDto(Course entity){
        return new CourseDTO(entity.getId(), entity.getName(), entity.getTeacherId(), entity.getMaxStudents());
    }
}

