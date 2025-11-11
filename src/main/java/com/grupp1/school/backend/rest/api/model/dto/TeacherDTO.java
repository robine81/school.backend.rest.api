package com.grupp1.school.backend.rest.api.model.dto;

import jakarta.validation.constraints.*;

public class TeacherDTO {

    @Positive(message = "ID must be positive")
    private Integer id;
    @Min(value = 18, message = "A teacher must be 18 or above")
    private int age;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "name must contain at least 3 characters")
    private String name;
    @Email
    @NotBlank(message = "E-mail is mandatory")
    private String email;

    public TeacherDTO(){}

    public TeacherDTO(Integer id, int age, String name, String email){
        this.id = id;
        this.age = age;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge () {
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
        return "TeacherDTO{" +
                "age=" + age +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
