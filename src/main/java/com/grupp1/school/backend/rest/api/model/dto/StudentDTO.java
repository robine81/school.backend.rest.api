package com.grupp1.school.backend.rest.api.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudentDTO {
    @NotBlank(message= "Student name must be specified")
    private String studentName;
    @Min(18)
    private int studentAge;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid format")
    private String studentEmail;

    public StudentDTO() {}

    public StudentDTO(String studentName, int studentAge, String studentEmail) {
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentEmail = studentEmail;
    }

    public String getStudentName() { return studentName; }

    public void setStudentName(String studentName) { this.studentName = studentName; }

    public int getStudentAge() { return studentAge; }

    public void setStudentAge(int studentAge) { this.studentAge = studentAge; }

    public String getStudentEmail() { return studentEmail; }

    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "studentName='" + studentName + '\'' +
                ", studentAge=" + studentAge +
                ", studentEmail='" + studentEmail + '\'' +
                '}';
    }
}
