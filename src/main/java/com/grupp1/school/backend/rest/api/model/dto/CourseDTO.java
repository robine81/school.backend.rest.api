package com.grupp1.school.backend.rest.api.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class CourseDTO {
    @NotNull(message = "Name is required.")
    @Length(min = 8, message = "Course name has to be at least 8 characters long.")
    String name;

    @Positive(message = "Teacher ID must be a positive integer.")
    Integer teacherId;

    @Min(value = 10, message = "A course must allow for a minimum of 10 students.")
    @NotNull(message = "Student limit is required.")
    Integer maxStudents;

    public CourseDTO(String name, Integer teacherId, Integer maxStudents) {
        this.name = name;
        this.teacherId = teacherId;
        this.maxStudents = maxStudents;
    }

    public String getName() {
        return name;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }
}
