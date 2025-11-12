package com.grupp1.school.backend.rest.api.model.dto;

import com.grupp1.school.backend.rest.api.model.Course;
import com.grupp1.school.backend.rest.api.model.Student;

public class EnrolmentResponseDTO {
    private Long id;
    private Long studentId;
    private Long courseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

}
