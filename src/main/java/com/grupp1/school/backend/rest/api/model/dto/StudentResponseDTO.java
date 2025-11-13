package com.grupp1.school.backend.rest.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder({"id", "name", "email", "age"})
public class StudentResponseDTO {

    Long id;
    String name;
    String email;
    int age;

    public StudentResponseDTO() {}

    public StudentResponseDTO(String studentName, int studentAge, String studentEmail) {
        this.name = studentName;
        this.email = studentEmail;
        this.age = studentAge;
    }

    public StudentResponseDTO(Long id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    @JsonProperty("id")
    public Long getStudentId() { return id; }
    public void setStudentId(Long id) { this.id = id; }

    @JsonProperty("name")
    public String getStudentName() { return name; }
    public void setStudentName(String studentName) { this.name = studentName; }

    @JsonProperty("email")
    public String getStudentEmail() { return email; }
    public void setStudentEmail(String studentEmail) { this.email = studentEmail; }

    @JsonProperty("age")
    public int getStudentAge() { return age; }
    public void setStudentAge(int studentAge) { this.age = studentAge; }


    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
