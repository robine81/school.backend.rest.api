package com.grupp1.school.backend.rest.api.model.dto;

import com.grupp1.school.backend.rest.api.model.Course;
import jakarta.validation.constraints.*;

import java.util.List;

public class TeacherRequestDTO {

    @Positive(message = "ID must be positive")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "name must contain at least 3 characters")
    private String name;

    @Email
    @NotBlank(message = "E-mail is mandatory")
    private String email;

    private List<Long> courseList;

    public TeacherRequestDTO(Long id, String name, String email, List<Long> courseList){
        this.id = id;
        this.name = name;
        this.email = email;
        this.courseList = courseList;

        //TODO add courses or refactor
    }

    public TeacherRequestDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Long> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Long> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString () {
        return "TeacherDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
