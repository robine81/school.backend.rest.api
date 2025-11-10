package com.grupp1.school.backend.rest.api.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TeacherDTO {

    @Min(18)
    private int age;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Email
    @NotBlank(message = "E-mail is mandatory")
    private String email;

    public TeacherDTO(){}

    public TeacherDTO(int age, String name, String email){
        this.age = age;
        this.name = name;
        this.email = email;
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
