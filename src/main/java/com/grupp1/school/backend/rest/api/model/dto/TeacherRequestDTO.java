package com.grupp1.school.backend.rest.api.model.dto;

import jakarta.validation.constraints.*;
import java.util.Set;

public class TeacherRequestDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "name must contain at least 3 characters")
    private String name;

    @Email
    @NotBlank(message = "E-mail is mandatory")
    private String email;

    private Set<Long> courses;

    public TeacherRequestDTO(){}

    public TeacherRequestDTO(Long id, String name, String email, Set<Long> courses){
        this.id = id;
        this.name = name;
        this.email = email;
        this.courses = courses;
    }

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

    public Set<Long> getCourses() {
        return courses;
    }
    public void setCourseList(Set<Long> courses) {
        this.courses = courses;
    }
}
