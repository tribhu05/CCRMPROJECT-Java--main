package edu.ccrm.service;

import edu.ccrm.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    void addStudent(Student s);
    List<Student> listStudents();
    Optional<Student> findByRegNo(String regNo);
    void deactivateStudent(String regNo);
}
