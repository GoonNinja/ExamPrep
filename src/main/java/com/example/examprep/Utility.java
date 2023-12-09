package com.example.examprep;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Utility {
    public static List<Student> getStudentsFromFile(String fileName) {
        Gson gson = new Gson();
        // This is called try...with resources when we use the ().
        // Anything created inside the ( ) will automatically have the .close() called once
        // the resource is not required.
        try (
                FileReader fileReader = new FileReader(fileName);
                JsonReader jsonReader = new JsonReader(fileReader);
        ) {
            Student[] students = gson.fromJson(jsonReader, Student[].class);
            return Arrays.asList(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
