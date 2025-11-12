package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByTeacherId(Integer teacherId);

    List<Course> findByName(String name);
    boolean existsByName(String name);
}
