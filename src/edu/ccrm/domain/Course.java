package edu.ccrm.domain;

import java.util.Objects;

public class Course {
    private final CourseCode code;
    private String title;
    private final int credits;
    private String instructorName;
    private final Semester semester;
    private String department;

    private Course(Builder b) {
        this.code = b.code;
        this.title = b.title;
        this.credits = b.credits;
        this.instructorName = b.instructorName;
        this.semester = b.semester;
        this.department = b.department;
    }

    public CourseCode getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getInstructorName() { return instructorName; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return String.format("%s - %s (%dcr) [%s] - %s", code, title, credits, semester, department);
    }

    public static class Builder {
        private final CourseCode code;
        private String title = "Untitled";
        private int credits = 3;
        private String instructorName = "TBD";
        private Semester semester = Semester.FALL;
        private String department = "General";

        public Builder(String code) { this.code = new CourseCode(code); }
        public Builder title(String t){ this.title = t; return this; }
        public Builder credits(int c){ this.credits = c; return this; }
        public Builder instructor(String i){ this.instructorName = i; return this; }
        public Builder semester(Semester s){ this.semester = s; return this; }
        public Builder department(String d){ this.department = d; return this; }
        public Course build() {
            Objects.requireNonNull(code);
            if (credits <= 0) throw new IllegalArgumentException("credits must be > 0");
            return new Course(this);
        }
    }
}