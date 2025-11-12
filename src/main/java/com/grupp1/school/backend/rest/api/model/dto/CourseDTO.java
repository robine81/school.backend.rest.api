package com.grupp1.school.backend.rest.api.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class CourseDTO {
    @Positive(message = "ID must be positive.")
    Long id;

    @Length(min = 8, message = "Course name has to be at least 8 characters long.")
    String name;

    @Min(value = 10, message = "A course must allow for a minimum of 10 students.")
    Integer maxStudents;

    public CourseDTO(Long id, String name, Integer maxStudents) {
        this.id = id;
        this.name = name;
        this.maxStudents = maxStudents;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }
}
