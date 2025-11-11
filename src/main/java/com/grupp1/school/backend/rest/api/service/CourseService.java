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
        return mapEntityToDto(repository.save(entity));
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

    public List<CourseDTO> getByName(String name){
        return repository.findByName(name).stream().map(this::mapEntityToDto).toList();
    }

    public Optional<CourseDTO> update(CourseDTO dto){
        Optional<Course> existing = repository.findById(dto.getId());
        if (existing.isPresent()) {
            if(dto.getName() != null){
                existing.get().setName(dto.getName());
            }
            if(dto.getTeacherId() != null){
                existing.get().setTeacherId(dto.getTeacherId());
            }
            if(dto.getMaxStudents() != null){
                existing.get().setMaxStudents(dto.getMaxStudents());
            }
            repository.save(existing.get());
        }
        return existing.map(this::mapEntityToDto);
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
