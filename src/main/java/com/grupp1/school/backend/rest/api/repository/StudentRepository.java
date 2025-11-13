package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    public List<Student> findByStudentName(String name);
    public List<Student> findByStudentEmail(String email);
    public List<Student> findByStudentNameContainingIgnoreCase(String name);
    public List<Student>  findByStudentNameContainingIgnoreCaseAndStudentAge(String name, int age);
    public List<Student> findByStudentAge(int age);
    public List<Student> findByStudentAgeBetween(int min, int max);

    boolean existsByStudentEmail(String email);
}
