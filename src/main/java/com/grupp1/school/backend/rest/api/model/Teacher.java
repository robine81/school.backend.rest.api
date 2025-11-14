package com.grupp1.school.backend.rest.api.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {

   private @Id
   @GeneratedValue Long id;
   private String name;
   private String email;

   @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
   private Set<Course> courses;

   public Teacher(){}

   public Set<Course> getCourses() {
        return courses;
   }
   public void setCourses(Set<Course> courses) {
       this.courses = courses;
   }

   public void setId(Long id) {
        this.id = id;
    }
   public Long getId () {
        return id;
    }


   public String getName () {
        return name;
    }
   public void setName (String name) {
        this.name = name;
    }


   public String getEmail () {
        return email;
    }
   public void setEmail (String email) {
        this.email = email;
    }
}
