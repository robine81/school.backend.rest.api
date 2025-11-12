package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;


public interface EnrolmentRepository extends JpaRepository<Enrolment, Long>
{
    List<Enrolment> findByStudentStudentIdAndCourseId(Integer studentId, Integer courseId);

    //boolean existsByStudentAndCourse(Student student, Course course);
    boolean existsByStudentStudentIdAndCourseId(Integer studentId, Integer courseId);
}
/*
@Repository
public class EnrolmentRepository {
    private List<Enrolment> list = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger();

    public Enrolment save(Enrolment enrolment) {
        Enrolment createdEnrolment = new Enrolment(
                idCounter.getAndIncrement(),
                enrolment.getStudentID(),
                enrolment.getCourseId()
        );
        list.add(enrolment);
        return createdEnrolment;
    }

    public Optional<Enrolment> getByStudentIDAndCourseID(Integer studentID, Integer courseID) {
        return this.list.stream()
                .filter(e -> e.getStudentID().equals(studentID) && e.getCourseId().equals(courseID))
                .findFirst();
    }

    public boolean existsByStudentIdAndCourseId(Integer studentID, Integer courseID) {
        return getByStudentIDAndCourseID(studentID, courseID).isPresent();
    }

    public List<Enrolment> listEnrolments()
    {
        return Collections.unmodifiableList(this.list);
    }
}
*/