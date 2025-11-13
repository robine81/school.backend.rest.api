package com.grupp1.school.backend.rest.api.model.dto;

public class CourseResponseDTO {
    Long id;
    String name;
    Integer maxStudents;
    String teacher;

    public CourseResponseDTO(Long id, String name, Integer maxStudents, String teacher) {
        this.id = id;
        this.name = name;
        this.maxStudents = maxStudents;
        this.teacher = teacher;
    }

    public CourseResponseDTO() {
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

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
