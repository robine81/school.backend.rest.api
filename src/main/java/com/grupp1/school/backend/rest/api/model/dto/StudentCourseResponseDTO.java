package com.grupp1.school.backend.rest.api.model.dto;

import java.util.List;

public class StudentCourseResponseDTO {
    Long id;
    String name;
    String email;
    Integer age;
    List<CourseGradeResponseDTO> courses;

    public StudentCourseResponseDTO() {}

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<CourseGradeResponseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseGradeResponseDTO> courses) {
        this.courses = courses;
    }
}
