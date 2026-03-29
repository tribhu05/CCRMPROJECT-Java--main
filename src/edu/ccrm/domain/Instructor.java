package edu.ccrm.domain;

public class Instructor extends Person {
    public Instructor(String fullName, String email) {
        super(fullName, email);
    }
    @Override
    public String getRole() { return "Instructor"; }
}
