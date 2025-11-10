package com.grupp1.school.backend.rest.api.model;

public class Enrolment {
    private Integer id;
    private Integer studentID;
    private Integer courseId;

    public Enrolment(Integer id, Integer studentID, Integer courseId) {
        this.id = id;
        this.studentID = studentID;
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
