package com.grupp1.school.backend.rest.api.repository;

import com.grupp1.school.backend.rest.api.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    List<Course> courses = new ArrayList<>();

    public List<Course> findAll() {
        return courses;
    }

    public Course save(Course course){
        course.setId(courses.stream().mapToInt(Course::getId).max().orElse(0) + 1);
        courses.add(course);
        return course;
    }

    public Optional<Course> findById(Integer id){
        return courses.stream().filter(course -> course.getId() == id && id != null).findFirst();
    }

    public List<Course> findByTeacherId(Integer teacherId){
        return courses.stream().filter(course -> course.getTeacherId() == teacherId && teacherId != null).toList();
    }

    public Course update(Integer id, String name, Integer teacherId, Integer maxStudents){
        Optional<Course> course = courses.stream().filter(c -> c.getId() == id && id != null).findFirst();
        if(course.isPresent()){
            if (name != null){
                course.get().setName(name);
            }
            if (teacherId != null){
                course.get().setTeacherId(teacherId);
            }
            if (maxStudents != null){
                course.get().setMaxStudents(maxStudents);
            }
            return course.get();
        }
        return null;
    }

    public boolean removeById(Integer id){
        return courses.removeIf(course -> course.getId() == id);
    }
}
