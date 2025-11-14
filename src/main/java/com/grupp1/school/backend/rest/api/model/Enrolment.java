package com.grupp1.school.backend.rest.api.model;

import com.grupp1.school.backend.rest.api.model.dto.EnrolmentResponseDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "enrolments")
public class Enrolment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

    @Column(nullable = true)
    private Integer grade;

    public Enrolment(Student student, Course course, Integer grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Enrolment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public EnrolmentResponseDTO toResponseDTO() {
        EnrolmentResponseDTO e = new EnrolmentResponseDTO();
        e.setId(this.getId());
        e.setCourseId(this.getCourse().getId());
        e.setStudentId(this.getStudent().getStudentId());
        return e;
    }

}
