// The Course class represents a course with associated information.
// It uses the Gson library annotations for JSON serialization/deserialization.

package com.example.examprep;

import com.google.gson.annotations.SerializedName;

public class Course {

    // Serialized name for JSON mapping
    @SerializedName("CourseName")
    private String courseName;

    // Grade for the course
    @SerializedName("GRADE")
    private int grade;

    // Course code
    private String courseCode;

    // Getter method for retrieving the course name
    public String getCourseName() {
        return courseName;
    }

    // Getter method for retrieving the grade
    public int getGrade() {
        return grade;
    }

    // Getter method for retrieving the course code
    public String getCourseCode() {
        return courseCode;
    }

    // Overridden toString method for generating a formatted string representation
    @Override public String toString() {
        return String.format("%s: %d", courseName, grade);
    }
}
