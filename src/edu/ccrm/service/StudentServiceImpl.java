package edu.ccrm.service;

import edu.ccrm.domain.Student;

import java.util.*;

public class StudentServiceImpl implements StudentService {
    private final Map<String, Student> byReg = new HashMap<>();

    @Override
    public void addStudent(Student s) { byReg.put(s.getRegNo(), s); }

    @Override
    public List<Student> listStudents() { return new ArrayList<>(byReg.values()); }

    @Override
    public Optional<Student> findByRegNo(String regNo) { return Optional.ofNullable(byReg.get(regNo)); }

    @Override
    public void deactivateStudent(String regNo) {
        Student s = byReg.get(regNo);
        if (s != null) s.deactivate();
    }
}
