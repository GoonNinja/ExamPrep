package com.example.examprep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TempTester {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Load students from a file
        List<Student> students = Utility.getStudentsFromFile("students.json");

        // Count passing students with an average grade greater than 50
        List<Student> passingStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getAvgGrade() > 50) {
                passingStudents.add(student);
            }
        }
        System.out.printf("Passing avg students: %d%n", passingStudents.size());

        // Count students passing the COMP1008 course with a grade greater than 50
        ArrayList<Student> comp1008Students = new ArrayList<>();
        for (Student student : students) {
            List<Course> courses = student.getCourses();

            for (Course course : courses) {
                if (course.getCourseCode().equals("COMP1008") && course.getGrade() > 50) {
                    comp1008Students.add(student);
                }
            }
        }
        System.out.printf("%nNumber of students passing COMP1008: %d%n", comp1008Students.size());

        // Count students taking 4 or more courses
        int fourPlusCourses = 0;
        for (Student student : students) {
            List<Course> courses = student.getCourses();
            int noOfCourses = 0;
            for (Course course : courses) {
                noOfCourses++;
            }
            if (noOfCourses > 3) {
                fourPlusCourses++;
            }
        }
        System.out.printf("%nNo of students taking 4 or more courses: %d%n", fourPlusCourses);


        // Count students passing at least four courses
        int fourOrMoreCoursePassingStudents = 0;
        for (Student student : students) {
            List<Course> courses = student.getCourses();
            int passingCourses = 0;
            for (Course course : courses) {
                if (course.getGrade() >= 50) {
                    passingCourses++;
                }
            }
            if (passingCourses >= 4)
                fourOrMoreCoursePassingStudents++;
        }

        System.out.printf("%nNumber of students passing at least four courses: %d%n", fourOrMoreCoursePassingStudents);


        // Calculate and print the average grade in COMP1113
        ArrayList<Course> comp1113Grades = new ArrayList<>();
        double sum = 0;

        for (Student student : students) {
            List<Course> courses = student.getCourses();
            for (Course course : courses) {
                if (course.getCourseCode().contains("COMP1113")) {
                    comp1113Grades.add(course);
                    sum += course.getGrade();
                }
            }
        }
        double totalNumberOfGrades = comp1113Grades.size();
        double avgGradeComp1113 = sum / totalNumberOfGrades;
        System.out.printf("%nAverage grade in Comp 1113: %.2f / %.2f = %.2f%n", sum, totalNumberOfGrades, avgGradeComp1113);


        // Calculate and print the average grade in COMP1113 using Java streams
        List<Course> comp1113StreamGrades = students.stream()  // Start streaming the students
                .flatMap(student -> student.getCourses().stream())  // Flatten the nested list of courses for each student
                .filter(course -> course.getCourseCode().contains("COMP1113"))  // Filter only COMP1113 courses
                .collect(Collectors.toList());  // Collect the filtered courses into a new list

        double streamSum = comp1113StreamGrades.stream()  // Start streaming the filtered COMP1113 courses
                .mapToDouble(Course::getGrade)  // Map each course to its grade as a double
                .sum();  // Sum up all the grades

        double totalStreamNumberOfGrades = comp1113Grades.size();  // This should be comp1113StreamGrades.size() instead of comp1113Grades.size()
        double avgStreamGradeComp1113 = totalNumberOfGrades > 0 ? streamSum / totalNumberOfGrades : 0.0;  // Calculate the average grade, handling the case of no grades

        System.out.printf(
                "%nAverage grade in Comp 1113 (using a stream): %.2f / %.2f = %.2f%n",
                streamSum,
                totalStreamNumberOfGrades,
                avgStreamGradeComp1113
        );  // Print the calculated average grade in COMP1113 using formatted output



        // Find and print the best student based on average grade
        Student bestStudent = students.get(0);
        for (Student student : students) {
            if (student.getAvgGrade() > bestStudent.getAvgGrade()) {
                bestStudent = student;
            }
        }
        System.out.println("\nBest student: " + bestStudent);


        // Create a separate thread to find and print the worst student based on average grade
        Thread worstStudentThread = new Thread("worstStudentThread") {  // Create a new thread with the name "worstStudentThread"
            @Override
            public void run() {  // Override the run() method to define the thread's behavior
                try {
                    Student worstStudent = students.get(0);  // Assume the first student is the worst initially
                    for (Student student : students) {  // Iterate through each student
                        if (student.getAvgGrade() < worstStudent.getAvgGrade()) {  // Compare the average grades of students
                            worstStudent = student;  // If a student with a lower average grade is found, update the worst student
                        }
                    }
                    System.out.println("Worst Student: " + worstStudent);  // Print the worst student found
                } catch (RuntimeException e) {  // Catch any runtime exceptions that might occur during the thread execution
                    throw new RuntimeException(e);  // Re-throw the exception to propagate it further
                }
            }
        };
        worstStudentThread.start();  // Start the thread's execution asynchronously

    }
}
