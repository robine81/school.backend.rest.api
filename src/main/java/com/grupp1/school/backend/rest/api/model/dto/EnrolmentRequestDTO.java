package com.grupp1.school.backend.rest.api.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EnrolmentRequestDTO {
    private Long id;
    @NotNull
    private Long studentID;
    @NotNull
    private Long courseID;
    @Positive(message = "Grade needs to be positive.")
    @Max(value = 100, message = "Grade cannot exceed 100.")
    private Integer grade;

    public EnrolmentRequestDTO(Long id, Long studentID, Long courseID, Integer grade) {
        this.id = id;
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentID() {
        return studentID;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
