package com.grupp1.school.backend.rest.api.service;

import com.grupp1.school.backend.rest.api.exception.ResourceAlreadyExistsException;
import com.grupp1.school.backend.rest.api.exception.ResourceNotFoundException;
import com.grupp1.school.backend.rest.api.model.Course;
import com.grupp1.school.backend.rest.api.model.Teacher;
import com.grupp1.school.backend.rest.api.model.dto.TeacherRequestDTO;
import com.grupp1.school.backend.rest.api.model.dto.TeacherResponseDTO;
import com.grupp1.school.backend.rest.api.repository.CourseRepository;
import com.grupp1.school.backend.rest.api.repository.TeacherRepository;
import com.grupp1.school.backend.rest.api.service.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository repository;
    private final CourseRepository courseRepository;

    public TeacherService (TeacherRepository repository, CourseRepository courseRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    public List<TeacherResponseDTO> getAll(){
        return repository.findAll()
                .stream().map(TeacherMapper::toResponse)
                .toList();
    }

    public TeacherResponseDTO getById(Long id){
        return repository.findById(id).map(TeacherMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find teacher with id " + id));
    }

    public TeacherResponseDTO getByEmail(String email){
        return repository.findByEmail(email).map(TeacherMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find teacher with email " + email));
    }

    public List<TeacherResponseDTO> getByName(String name){
        return repository.findByName(name).stream().map(TeacherMapper::toResponse).toList();
    }

    public TeacherResponseDTO addTeacher(TeacherRequestDTO dto){
        if(repository.existsByEmail(dto.getEmail())) {
            throw new ResourceAlreadyExistsException("This teacher already exists");
        } else {
            Teacher teacher = TeacherMapper.toEntity(dto);
            teacher.setCourseList(dto.getCourseList().stream()
                    .map((id) -> this.convertCourseIdToCourse(id, teacher)).toList());

            return  TeacherMapper.toResponse(repository.save(teacher));
        }
    }

    public boolean deleteById(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public TeacherResponseDTO update(Long id, TeacherRequestDTO dto){

        Optional<Teacher> opt = repository.findById(id);

        if (opt.isPresent()){
            Teacher existing = opt.get();
            existing.setName(dto.getName());
            existing.setEmail(dto.getEmail());
            // TODO figure out why this throws an exception
//            existing.setCourseList(dto.getCourseList().stream()
//                    .map((courseId) -> this.convertCourseIdToCourse(courseId, existing)).toList());
            return TeacherMapper.toResponse(repository.save(existing));
        }

        return null;
    }


    private Course convertCourseIdToCourse(Long courseId, Teacher teacher){
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isPresent()){
            if(optionalCourse.get().getTeacher() != null){
                throw new ResourceAlreadyExistsException("Course with id " + courseId + " is already being taught by " + optionalCourse.get().getTeacher().getName());
            }
            optionalCourse.get().setTeacher(teacher);
            return optionalCourse.get();
        } else {
            throw new ResourceNotFoundException("No course exists with id " + courseId);
        }
    }
}
