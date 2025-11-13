package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.exception.ResourceAlreadyExistsException;
import com.grupp1.school.backend.rest.api.exception.ResourceNotFoundException;
import com.grupp1.school.backend.rest.api.model.Course;
import com.grupp1.school.backend.rest.api.model.Enrolment;
import com.grupp1.school.backend.rest.api.model.dto.CourseRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentResponseDTO;
import com.grupp1.school.backend.rest.api.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository){
        this.repository = repository;
    }

    public CourseRequestDTO create(CourseRequestDTO dto){
        Course entity = mapDtoToEntity(dto);
        if (repository.existsByName(dto.getName())) throw new ResourceAlreadyExistsException("Course already exists.");
        return mapEntityToDto(repository.save(entity));
    }

    public List<CourseRequestDTO> getAll(){
        return repository.findAll().stream().map(this::mapEntityToDto).toList();
    }

    public CourseRequestDTO getById(Long id){
        return repository.findById(id).map(this::mapEntityToDto).orElseThrow(() -> new ResourceNotFoundException("Couldn't find course with id = " + id));
    }

    public List<CourseRequestDTO> getByName(String name){
        return repository.findByName(name).stream().map(this::mapEntityToDto).toList();
    }

    public CourseRequestDTO update(CourseRequestDTO dto){
        Course existing = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Couldn't find course with id = " + dto.getId()));
        if(dto.getName() != null){
            existing.setName(dto.getName());
        }
        if(dto.getMaxStudents() != null){
            existing.setMaxStudents(dto.getMaxStudents());
        }
        repository.save(existing);

        return mapEntityToDto(existing);
    }

    public boolean deleteById(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<StudentResponseDTO> getEnrolledStudentsById(Long id){
        List<StudentResponseDTO> students = new ArrayList<>();
        Optional<Course> course = repository.findById(id);
        if (course.isPresent()){
            students.addAll(course.get().getEnrolments().stream()
                    .map(Enrolment::getStudent)
                    .map(StudentService::toResponseDTO).toList());
        }
        return students;
    }

    private Course mapDtoToEntity(CourseRequestDTO dto){
        return new Course(dto.getId(), dto.getName(), dto.getMaxStudents());
    }

    private CourseRequestDTO mapEntityToDto(Course entity){
        return new CourseRequestDTO(entity.getId(), entity.getName(), entity.getMaxStudents());
    }
}
