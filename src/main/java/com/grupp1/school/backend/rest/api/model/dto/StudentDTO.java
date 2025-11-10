package com.grupp1.school.backend.rest.api.model.dto;

public class StudentDTO {
    private int studentId;
    private String studentName;
    private int studentAge;
    private String studentEmail;

    public StudentDTO() {}

    public StudentDTO(int studentId, String studentName, int studentAge, String studentEmail) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentEmail = studentEmail;
    }

    public int getStudentId() { return studentId; }

    public void setStudentId(int studentId) { this.studentId = studentId;}

    public String getStudentName() { return studentName; }

    public void setStudentName(String studentName) { this.studentName = studentName; }

    public int getStudentAge() { return studentAge; }

    public void setStudentAge(int studentAge) { this.studentAge = studentAge; }

    public String getStudentEmail() { return studentEmail; }

    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
}
