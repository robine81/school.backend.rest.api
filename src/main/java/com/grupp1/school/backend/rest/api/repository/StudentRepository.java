package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    public List<Student> findByName(String name);
    public List<Student> findByEmail(String email);
    public List<Student> findByAge(int age);
}
