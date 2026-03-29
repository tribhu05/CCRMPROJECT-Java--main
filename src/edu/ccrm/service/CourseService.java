package edu.ccrm.service;

import edu.ccrm.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    void addCourse(Course c);
    List<Course> listCourses();
    Optional<Course> findByCode(String code);
    List<Course> searchByInstructor(String instructor);
}
