package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

    public List<Teacher> findByName(String name);

    public Optional<Teacher> findByEmail(String email);
}
