package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s where LOWER(s.studentName) LIKE LOWER(CONCAT('%', :name_keyword, '%'))")
    List<Student> searchByName(@Param("name_keyword") String name);
    @Query("SELECT s FROM Student s WHERE s.studentAge BETWEEN :min AND :max")
    List<Student> searchByAgeBetween(@Param("min")int min, @Param("max") int max);
    @Query("SELECT s FROM Student s WHERE LOWER(s.studentName) LIKE LOWER(CONCAT('%', :name, '%')) AND s.studentAge = :age")
    List<Student> searchByNameAndAge(@Param("name") String name, @Param("age") int age);
    @Query("SELECT s FROM Student s WHERE s.studentAge = :age")
    List<Student> searchByAge(@Param("age") int age);

    public List<Student> findByStudentName(String name);
    public List<Student>  findByStudentNameContainingIgnoreCaseAndStudentAge(String name, int age);
    public List<Student> findByStudentEmail(String email);
    public List<Student> findByStudentNameContainingIgnoreCase(String name);
    public List<Student> findByStudentAge(int age);
    public List<Student> findByStudentAgeBetween(int min, int max);
    boolean existsByStudentEmail(String email);
}
