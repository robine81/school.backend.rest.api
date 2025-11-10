package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    public List<Student> findAll(){ return students; }

    public Optional<Student> findById(int id){
        return students.stream()
                .filter(s -> s.getStudentId() == id)
                .findFirst();
    }

    public List<Student> findByName(String name) {
        return students.stream()
                .filter(s -> s.getStudentName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .toList();
    }

    public Student save(Student student){
        int nextId = students.stream()
                .mapToInt(s -> s.getStudentId())
                .max()
                .orElse(0) +1;
        student.setStudentId(nextId);
        students.add(student);
        return student;
    }

    public boolean deleteById(int id) { return students.removeIf(s -> s.getStudentId() == id); }
}
