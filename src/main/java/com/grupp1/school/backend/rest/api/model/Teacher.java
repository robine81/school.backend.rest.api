package com.grupp1.school.backend.rest.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private int age;
   private String name;
   private String email;

   public Teacher(){}

    public Teacher(Integer id, int age, String name, String email){
       this.id = id;
       this.age = age;
       this.name = name;
       this.email = email;
    }

    public Integer getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getAge () {
        return age;
    }

    public void setAge (int age) {
        this.age = age;
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
                "age=" + age +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
