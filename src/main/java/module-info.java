module com.example.examprep {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.examprep to javafx.fxml, com.google.gson;
    exports com.example.examprep;
}