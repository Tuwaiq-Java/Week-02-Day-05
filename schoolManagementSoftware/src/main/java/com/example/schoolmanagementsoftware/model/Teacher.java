package com.example.schoolmanagementsoftware.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
public class Teacher {

    @NotEmpty(message = "Id cannot be empty")
    @Size(min = 2)
    private String id;

    @NotEmpty(message = "Id cannot be empty")
    @Size(min = 2)
    private String name;

    private ArrayList<Course> courses;

    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }
}
