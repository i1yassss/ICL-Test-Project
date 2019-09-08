package com.java.dto;

import com.java.model.Student;

public class StudentRating {

    Student student;
    double averageRating;

    public StudentRating(Student student, double averageRating) {
        this.student = student;
        this.averageRating = averageRating;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
