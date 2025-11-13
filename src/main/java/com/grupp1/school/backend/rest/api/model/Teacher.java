package com.grupp1.school.backend.rest.api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

   private @Id
   @GeneratedValue Long id;
   private String name;
   private String email;
   private int age;

   @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
   private List<Course> courseList = List.of();

   public Teacher(Long id, String name, String email, List<Course> courseList){
       this.id = id;
       this.name = name;
       this.email = email;
       this.courseList = courseList;
   }

   public Teacher(){}

    public Teacher(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public List<Course> getCourseList() {
        return courseList;
   }

   public void setCourseList(List<Course> courses) {
       this.courseList = courses;
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

    @Override
    public String toString () {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
