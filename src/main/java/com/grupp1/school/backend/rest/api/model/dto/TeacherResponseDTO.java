package com.grupp1.school.backend.rest.api.model.dto;

import com.grupp1.school.backend.rest.api.model.Course;

import java.util.List;

public class TeacherResponseDTO {
    private Long id;
    private String name;
    private String email;
    private List<CourseResponseDTO> courseList;

    //TODO use DTO
    public TeacherResponseDTO(Long id, String name, String email, List<CourseResponseDTO> courseList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courseList = courseList;
    }

    public TeacherResponseDTO() {}


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

    public List<CourseResponseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseResponseDTO> courseList) {
        this.courseList = courseList;
    }
}
