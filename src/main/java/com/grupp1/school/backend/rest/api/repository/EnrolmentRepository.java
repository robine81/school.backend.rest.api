package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Enrolment;
import org.springframework.stereotype.Repository;

import java.lang.instrument.UnmodifiableClassException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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

    public boolean existsByStudentIDAndCourseID(Integer studentID, Integer courseID) {
        return getByStudentIDAndCourseID(studentID, courseID).isPresent();
    }

    public List<Enrolment> listEnrolments()
    {
        return Collections.unmodifiableList(this.list);
    }
}
