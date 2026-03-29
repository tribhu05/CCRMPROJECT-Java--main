package edu.ccrm.service;

import edu.ccrm.domain.*;
import edu.ccrm.exceptions.DuplicateEnrollmentException;
import edu.ccrm.exceptions.MaxCreditLimitExceededException;

import java.util.Optional;

public class EnrollmentServiceImpl implements EnrollmentService {
    private final StudentService studentService;
    private final CourseService courseService;
    private final int MAX_CREDITS_PER_SEM = 18;

    public EnrollmentServiceImpl(StudentService ss, CourseService cs) {
        this.studentService = ss;
        this.courseService = cs;
    }

    @Override
    public void enroll(String regNo, String courseCode) throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        Optional<Student> so = studentService.findByRegNo(regNo);
        Optional<Course> co = courseService.findByCode(courseCode);
        if (so.isEmpty()) throw new IllegalArgumentException("Student not found");
        if (co.isEmpty()) throw new IllegalArgumentException("Course not found");

        Student s = so.get();
        Course c = co.get();

        // duplicate check
        boolean already = s.getEnrollments().stream().anyMatch(e -> e.getCourseCode().equalsIgnoreCase(c.getCode().getCode()));
        if (already) throw new DuplicateEnrollmentException("Student already enrolled in " + c.getCode());

        // check credits for the same semester
        int currentCredits = s.getEnrollments().stream()
                .filter(e -> e.getSemester() == c.getSemester())
                .mapToInt(Enrollment::getCredits).sum();
        if (currentCredits + c.getCredits() > MAX_CREDITS_PER_SEM)
            throw new MaxCreditLimitExceededException("Enrolling exceeds max credits per semester (" + MAX_CREDITS_PER_SEM + ")");

        Enrollment en = new Enrollment(c);
        s.addEnrollment(en);
    }

    @Override
    public void recordMarks(String regNo, String courseCode, int marks) throws Exception {
        Student s = studentService.findByRegNo(regNo).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Enrollment e = s.getEnrollments().stream()
                .filter(x -> x.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
        e.recordMarks(marks);
    }
}