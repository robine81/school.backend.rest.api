package com.grupp1.school.backend.rest.api.model.dto;

import java.util.Set;

public class TeacherResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Set<CourseListDTO> courses;

    public TeacherResponseDTO() {}

    public TeacherResponseDTO(Long id, String name, String email, Set<CourseListDTO> courses) {
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


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public Set<CourseListDTO> getCourses() {
        return courses;
    }
    public void setCourses(Set<CourseListDTO> courses) {
        this.courses = courses;
    }
}
