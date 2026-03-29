package edu.ccrm.service;

import edu.ccrm.domain.Course;

import java.util.*;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {
    private final Map<String, Course> byCode = new HashMap<>();

    @Override
    public void addCourse(Course c) { byCode.put(c.getCode().getCode(), c); }

    @Override
    public List<Course> listCourses() { return new ArrayList<>(byCode.values()); }

    @Override
    public Optional<Course> findByCode(String code) { return Optional.ofNullable(byCode.get(code.toUpperCase())); }

    @Override
    public List<Course> searchByInstructor(String instructor) {
        return byCode.values().stream()
                .filter(c -> c.getInstructorName().equalsIgnoreCase(instructor))
                .collect(Collectors.toList());
    }
}

