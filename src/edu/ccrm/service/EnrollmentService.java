package edu.ccrm.service;

import edu.ccrm.exceptions.DuplicateEnrollmentException;
import edu.ccrm.exceptions.MaxCreditLimitExceededException;

public interface EnrollmentService {
    void enroll(String regNo, String courseCode) throws DuplicateEnrollmentException, MaxCreditLimitExceededException;
    void recordMarks(String regNo, String courseCode, int marks) throws Exception;
}

