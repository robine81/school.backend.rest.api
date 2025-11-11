package com.grupp1.school.backend.rest.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer studentId;

    @Column(name = "name")
    private String studentName;
    @Column(name = "email")
    private String studentEmail;
    @Column(name = "age")
    private int studentAge;

    public Student(Integer studentId, String studentName, int studentAge, String studentEmail) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentEmail = studentEmail;
    }

    public Student() {}

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public int getStudentAge() { return studentAge; }
    public void setStudentAge(int studentAge) { this.studentAge = studentAge; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + studentId +
                ", name='" + studentName + '\'' +
                ", age=" + studentAge +
                ", email='" + studentEmail + '\'' +
                '}';
    }
}
