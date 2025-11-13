package com.grupp1.school.backend.rest.api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer maxStudents;

    @JoinColumn(name = "teacher")
    @ManyToOne
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrolment> enrolments;

    public List<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void setEnrolments(List<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }

    public Course(Long id, String name, Integer maxStudents) {
        this.id = id;
        this.name = name;
        this.maxStudents = maxStudents;
    }

    public Course() {}

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
