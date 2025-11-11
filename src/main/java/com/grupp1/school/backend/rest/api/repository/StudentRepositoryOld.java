package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryOld {
    private List<Student> students = new ArrayList<>();

    public StudentRepositoryOld() {
        students.add(new Student(1, "Harry Potter", 19, "harry@hogwarts.co.uk"));
        students.add(new Student(2, "Hermione", 19, "hermoine@hogwarts.co.uk"));
        students.add(new Student(3, "Ron", 19, "ron@hogwarts.co.uk"));
    }

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

    public List<Student> findByEmail(String email) {
        return students.stream()
                .filter(s -> s.getStudentEmail()
                        .toLowerCase()
                        .contains(email.toLowerCase()))
                .toList();
    }

    public List<Student> findByAge(int age) {
        return students.stream()
                .filter(s -> s.getStudentAge() == age)
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
