package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;


public interface EnrolmentRepository extends JpaRepository<Enrolment, Long>
{
    List<Enrolment> findByStudentStudentIdAndCourseId(Integer studentId, Integer courseId);

    boolean existsByStudentStudentIdAndCourseId(Long studentId, Long courseId);
}
