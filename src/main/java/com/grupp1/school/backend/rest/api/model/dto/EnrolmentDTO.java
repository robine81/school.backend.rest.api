package com.grupp1.school.backend.rest.api.model.dto;

public class EnrolmentDTO {
    @NotNull
    private Integer id;
    @NotNull
    private Integer studentID;
    @NotNull
    private Integer courseId;

    public EnrolmentDTO(Integer id, Integer studentID, Integer courseId) {
        this.id = id;
        this.studentID = studentID;
        this.courseId = courseId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }
}
