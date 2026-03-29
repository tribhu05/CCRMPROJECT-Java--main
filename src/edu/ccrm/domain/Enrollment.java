package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Enrollment {
    private final String courseCode;
    private final String courseTitle;
    private final int credits;
    private final Semester semester;
    @SuppressWarnings("unused")
	private final LocalDateTime enrolledAt;
    private Integer marks; // nullable
    private Grade grade;   // nullable

    public Enrollment(Course course) {
        Objects.requireNonNull(course);
        this.courseCode = course.getCode().getCode();
        this.courseTitle = course.getTitle();
        this.credits = course.getCredits();
        this.semester = course.getSemester();
        this.enrolledAt = LocalDateTime.now();
        this.marks = null;
        this.grade = null;
    }

    public String getCourseCode() { return courseCode; }
    public int getCredits() { return credits; }
    public Semester getSemester() { return semester; }

    public void recordMarks(int marks) {
        this.marks = marks;
        this.grade = Grade.fromMarks(marks);
    }
    public Grade getGrade() { return grade; }
    public Integer getMarks() { return marks; }

    @Override
    public String toString() {
        return String.format("%s - %s (%dcr) marks=%s grade=%s", courseCode, courseTitle, credits,
            marks==null?"N/A":marks.toString(), grade==null?"N/A":grade.name());
    }
}