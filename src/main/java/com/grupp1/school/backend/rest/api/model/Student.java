package com.grupp1.school.backend.rest.api.model;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int age;
    private String email;

    public Student(Integer studentId, String studentName, int studentAge, String studentEmail) {
        this.id = studentId;
        this.name = studentName;
        this.age = studentAge;
        this.email = studentEmail;
    }

    public Student() {}

    public Integer getStudentId() { return id; }

    public void setStudentId(Integer studentId) { this.id = studentId; }

    public String getStudentName() { return name; }

    public void setStudentName(String studentName) { this.name = studentName; }

    public int getStudentAge() { return age; }

    public void setStudentAge(int studentAge) { this.age = studentAge; }

    public String getStudentEmail() { return email; }

    public void setStudentEmail(String studentEmail) { this.email = studentEmail; }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + id +
                ", studentName='" + name + '\'' +
                ", studentAge=" + age +
                ", studentEmail='" + email + '\'' +
                '}';
    }
}
