package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Person {
    protected final String id;
    protected String fullName;
    protected String email;
    protected final LocalDateTime createdAt;

    protected Person(String fullName, String email) {
        this.id = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public void setFullName(String name) { this.fullName = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public abstract String getRole();

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", fullName, getRole(), email);
    }
}
