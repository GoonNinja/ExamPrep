// The StudentViewController class is a controller for the JavaFX application, responsible for handling UI events
// and updating the displayed data.

package com.example.examprep;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class StudentViewController {

    @FXML
    private TableColumn<Student, Integer> studentNumberColumn;

    @FXML
    private TableColumn<Student, String> firstNameColumn;

    @FXML
    private TableColumn<Student, String> lastNameColumn;

    @FXML
    private TableColumn<Student, String> averageGradeColumn;

    @FXML
    private TextField filterTextField;

    @FXML
    private ListView<Course> resultsListView;

    @FXML
    private Label resultsMsgLabel;

    @FXML
    private TableView<Student> studentTableView;

    private List<Student> allStudents;

    // Initialization method, called when the FXML file is loaded
    @FXML
    void initialize() {
        // Retrieve students from the file
        allStudents = Utility.getStudentsFromFile("students.json");

        // Set up cell value factories for each column
        studentNumberColumn.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        averageGradeColumn.setCellValueFactory(new PropertyValueFactory<>("formattedAvgGrade"));

        // Populate the table view with all students
        studentTableView.getItems().addAll(allStudents);

        // Update labels based on initial data
        updateLabels();

        // Listener for changes in the filter text field
        filterTextField.textProperty().addListener((observableValue, old, searchTerm) -> {
            // Clear the table view items
            studentTableView.getItems().clear();

            // Iterate through all students and add those matching the search term
            for (Student student : allStudents) {
                if (student.contains(searchTerm)) {
                    studentTableView.getItems().add(student);
                }
            }

            // Update labels based on filtered data
            updateLabels();
        });

        // Listener for changes in the selected item in the table view
        studentTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, old, studentSelected) -> {
            // Clear the results list view and add courses of the selected student
            resultsListView.getItems().clear();
            ArrayList<Course> courses = studentSelected.getCourses();
            resultsListView.getItems().addAll(courses);
        });
    }

    // Helper method to update labels with current data
    private void updateLabels() {
        resultsMsgLabel.setText("Total number of students: " + studentTableView.getItems().size());
    }
}
