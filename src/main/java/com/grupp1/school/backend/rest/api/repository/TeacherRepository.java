package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {

    private List<Teacher> teachers = new ArrayList<>();

    public TeacherRepository(){
        teachers.add(new Teacher(1, 300, "Albus Dumbledore", "a.dumbledore@hogwarts.wiz"));
        teachers.add(new Teacher(2, 289, "Severus Snape", "s.snape@hogwarts.wiz"));
        teachers.add(new Teacher(3, 180, "Maria Montessori", "m.montessori@hogwarts.wiz"));
    }

    public List<Teacher> getAll(){
        return teachers;
    }

   public Optional<Teacher> getById(int id){
        return teachers.stream().filter(t -> t.getId() == id).findFirst();
   }

   public Optional<Teacher> getbyEmail(String email){
        return teachers.stream().filter(t -> t.getEmail().equalsIgnoreCase(email)).findFirst();
   }

   public List<Teacher> getByName(String name){
        return teachers.stream()
                .filter(t -> t.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
   }

}
