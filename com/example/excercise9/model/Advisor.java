package com.example.excercise9.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Data
public class Advisor {

    @NotEmpty(message = "Id is required")
    private String id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Age is required")
    @Min(value = 18, message = "your age must be more than 18 years")
    private Integer age;

    @NotEmpty(message = "Course is required")
    private ArrayList<Course> courses;

    public Advisor(String id, String name, Integer age, ArrayList<Course> courses) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.courses = new ArrayList<>();
    }
}
