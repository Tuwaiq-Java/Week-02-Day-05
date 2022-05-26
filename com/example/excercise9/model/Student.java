package com.example.excercise9.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Data
public class Student {

    @NotEmpty(message = "Id is required")
    private String id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Age is required")
    @Min(value = 18, message = "your age must be more than 18 years")
    private Integer age;

    @NotEmpty(message = "Course is required")
    private ArrayList<Course> coursesList;

    @NotEmpty(message = "Advisor name is required")
    private String AdvisorName;

    @NotEmpty(message = "Major is required")
    private String major;


    public Student(String id, String name, Integer age, ArrayList<Course> coursesList, String advisorName, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.coursesList = new ArrayList<>();
        this.AdvisorName = advisorName;
        this.major = major;
    }
}
