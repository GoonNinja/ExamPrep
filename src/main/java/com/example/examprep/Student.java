// The Student class represents a student with associated information, including courses and methods for calculation.

package com.example.examprep;

import com.google.gson.annotations.SerializedName;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Student {

    // Serialized name for JSON mapping
    @SerializedName("STUDENTNUM")
    private int studentNumber;

    // Student's first name
    @SerializedName("FirstName")
    private String firstName;

    // Student's email
    @SerializedName("Email")
    private String email;

    // Student's last name
    private String lastName;

    // List of courses associated with the student
    private ArrayList<Course> courses;

    // Getter method for retrieving the student number
    public int getStudentNumber() {
        return studentNumber;
    }

    // Getter method for retrieving the first name
    public String getFirstName() {
        return firstName;
    }

    // Getter method for retrieving the email
    public String getEmail() {
        return email;
    }

    // Getter method for retrieving the last name
    public String getLastName() {
        return lastName;
    }

    // Getter method for retrieving the list of courses
    public ArrayList<Course> getCourses() {
        return courses;
    }

    // Overridden toString method for generating a formatted string representation of the student
    @Override
    public String toString() {
        return String.format("%s- %s- %s%nStudent average = %.1f%n", firstName, studentNumber, courses, getAvgGrade());
    }

    // Method for calculating the average grade of the student
    public double getAvgGrade(){

        return courses.stream()
                .mapToDouble(course -> course.getGrade())
                .average()
                .getAsDouble();

        //        if (courses.size()==0)
        //            return -1;
        //        else
        //        {
        //            double sum=0;
        //
        //            for (StudentCourse course : courses)
        //            {
        //                sum = sum + course.getGrade();
        //            }
        //            return sum/courses.size();
        //        }
    }

    // Method for checking if the student information contains a search term
    public boolean contains(String searchTerm) {
        String avgGrade = Double.toString(getAvgGrade());
        searchTerm = searchTerm.toLowerCase();

        // Contains is a case-sensitive search for substring
        return (firstName.toLowerCase().contains(searchTerm) ||
                Integer.toString(studentNumber).contains(searchTerm) ||
                lastName.toLowerCase().contains(searchTerm) ||
                email.contains(searchTerm) ||
                avgGrade.contains(searchTerm));
    }

    // Method for retrieving the formatted average grade
    public String getFormattedAvgGrade() {
        double averageGrade = getAvgGrade();

        // Format the result to one decimal point
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return decimalFormat.format(averageGrade);
    }
}
