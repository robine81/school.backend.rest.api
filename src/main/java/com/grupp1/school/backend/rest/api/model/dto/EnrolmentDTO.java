package com.grupp1.school.backend.rest.api.model.dto;

import jakarta.validation.constraints.NotNull;

public class EnrolmentDTO {
    private Long id;
    @NotNull
    private Long studentID;
    @NotNull
    private Long courseID;

    public EnrolmentDTO(Long id, Long studentID, Long courseID) {
        this.id = id;
        this.studentID = studentID;
        this.courseID = courseID;
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

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }
}
