package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Enrolment;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnrolmentRepository {
    public List<Enrolment> list = new ArrayList<>();

    public void add(Enrolment enrolment) {
        list.add(enrolment);
    }

}
