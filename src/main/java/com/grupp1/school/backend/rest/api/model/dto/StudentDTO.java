package com.grupp1.school.backend.rest.api.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentDTO {
    @NotNull
    private Integer id;
    @NotBlank(message= "Student name must be specified")
    private String name;
    @Min(18)
    private int age;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid format")
    private String email;

    public StudentDTO() {}

    public StudentDTO(String studentName, int studentAge, String studentEmail) {
        this.name = studentName;
        this.age = studentAge;
        this.email = studentEmail;
    }

    public StudentDTO (Integer id, String studentEmail, int studentAge, String studentName) {
        this.id = id;
        this.email = studentEmail;
        this.age = studentAge;
        this.name = studentName;
    }

    public Integer getStudentId() { return id; }

    public void setStudentId(Integer id) { this.id = id; }

    public String getStudentName() { return name; }

    public void setStudentName(String studentName) { this.name = studentName; }

    public int getStudentAge() { return age; }

    public void setStudentAge(int studentAge) { this.age = studentAge; }

    public String getStudentEmail() { return email; }

    public void setStudentEmail(String studentEmail) { this.email = studentEmail; }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "studentId=" + id +
                ", studentName='" + name + '\'' +
                ", studentAge=" + age +
                ", studentEmail='" + email + '\'' +
                '}';
    }
}
