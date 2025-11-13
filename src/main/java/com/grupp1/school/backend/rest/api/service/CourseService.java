package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.exception.ResourceAlreadyExistsException;
import com.grupp1.school.backend.rest.api.exception.ResourceNotFoundException;
import com.grupp1.school.backend.rest.api.model.Course;
import com.grupp1.school.backend.rest.api.model.Enrolment;
import com.grupp1.school.backend.rest.api.model.dto.CourseRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.CourseResponseDTO;
import com.grupp1.school.backend.rest.api.model.dto.StudentResponseDTO;
import com.grupp1.school.backend.rest.api.repository.CourseRepository;
import com.grupp1.school.backend.rest.api.repository.TeacherRepository;
import com.grupp1.school.backend.rest.api.service.mapper.CourseMapper;
import com.grupp1.school.backend.rest.api.service.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository repository;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository repository, TeacherRepository teacherRepository){
        this.repository = repository;
        this.teacherRepository = teacherRepository;
    }

    public CourseResponseDTO create(CourseRequestDTO dto){
        Course entity = CourseMapper.toEntity(dto);
        if (repository.existsByName(dto.getName()))
            throw new ResourceAlreadyExistsException("Course with name " + dto.getName() + " already exists.");
        if (dto.getTeacherId() != null && teacherRepository.existsById(dto.getTeacherId()))
            entity.setTeacher(teacherRepository.findById(dto.getTeacherId()).get());
        return CourseMapper.toResponseDTO(repository.save(entity));
    }

    public List<CourseResponseDTO> getAll(){
        return repository.findAll().stream().map(CourseMapper::toResponseDTO).toList();
    }

    public CourseResponseDTO getById(Long id){
        return repository.findById(id).map(CourseMapper::toResponseDTO).orElseThrow(() -> new ResourceNotFoundException("Couldn't find course with id = " + id));
    }

    public List<CourseResponseDTO> getByName(String name){
        return repository.findByName(name).stream().map(CourseMapper::toResponseDTO).toList();
    }

    public List<CourseResponseDTO> searchByName(String name){
        return repository.searchByName(name).stream().map(CourseMapper::toResponseDTO).toList();
    }

    public CourseResponseDTO update(CourseRequestDTO dto){
        Course existing = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Couldn't find course with id = " + dto.getId()));
        if(dto.getName() != null){
            existing.setName(dto.getName());
        }
        if(dto.getMaxStudents() != null){
            existing.setMaxStudents(dto.getMaxStudents());
        }
        if(dto.getTeacherId() != null && teacherRepository.existsById(dto.getTeacherId())){
            existing.setTeacher(teacherRepository.findById(dto.getTeacherId()).get());
        }
        repository.save(existing);

        return CourseMapper.toResponseDTO(existing);
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
                    .map(StudentMapper::toResponseDTO).toList());
        }
        return students;
    }
}