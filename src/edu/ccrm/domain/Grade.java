package edu.ccrm.domain;

public enum Grade {
    S(4.0), A(4.0), B(3.0), C(2.0), D(1.0), F(0.0);

    private final double points;
    Grade(double points) { this.points = points; }
    public double getPoints() { return points; }

    // simple conversion from marks to grade
    public static Grade fromMarks(int marks) {
        if (marks >= 85) return S;
        if (marks >= 75) return A;
        if (marks >= 65) return B;
        if (marks >= 55) return C;
        if (marks >= 45) return D;
        return F;
    }
}
