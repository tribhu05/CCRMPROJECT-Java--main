
package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends Person {
    private final String regNo;
    private boolean active;
    @SuppressWarnings("unused")
	private final LocalDateTime registeredAt;
    private final List<Enrollment> enrollments = new ArrayList<>();

    public Student(String regNo, String fullName, String email) {
        super(fullName, email);
        this.regNo = Objects.requireNonNull(regNo);
        this.active = true;
		this.registeredAt = LocalDateTime.now();
        
    }

    @Override
    public String getRole() { return "Student"; }

    public String getRegNo() { return regNo; }
    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; }

    public List<Enrollment> getEnrollments() { return enrollments; }

    public void addEnrollment(Enrollment e) { enrollments.add(e); }

    @Override
    public String toString() {
        return String.format("%s [%s] - %s (active=%s)", fullName, regNo, email, active);
    }
}
