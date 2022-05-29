package com.example.schoolmanagementsoftware.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.ArrayList;

@Data
public class Student {

    @NotEmpty(message = "Id cannot be empty")
    @Size(min = 2)
    private String id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2)
    private String name;

    @NotNull(message = "age cannot be empty")
    @Min(value=6 , message = "age cannot be less than 6")
    private Integer age;

    private ArrayList<Course> courses;

    @NotEmpty(message = "advisor cannot be empty")
    private String advisorName;

    @NotEmpty(message = "major cannot be empty")
    @Pattern(regexp = "(IT|CS|IS)")
    private String major;

    public Student(String id, String name, Integer age, String advisorName, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.courses = new ArrayList<>();
        this.advisorName = advisorName;
        this.major = major;
    }
}
