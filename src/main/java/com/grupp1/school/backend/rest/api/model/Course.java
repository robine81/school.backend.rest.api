package com.grupp1.school.backend.rest.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer teacherId;
    private Integer maxStudents;

    public Course(Integer id, String name, Integer teacherId, Integer maxStudents) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
        this.maxStudents = maxStudents;
    }

    public Course() {}

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
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
