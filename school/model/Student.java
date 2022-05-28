package com.example.school.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
@Data
public class Student {
    @NotEmpty(message = "id is required ")
    private String id ;
    @NotEmpty(message = "name is required ")
    private String name;
    @NotNull(message = "age is required ")
    private Integer age;
    private ArrayList<Classes> classList;
    @NotEmpty(message = "advisorName is required ")
    private String advisorName;
    @NotEmpty(message = "major is required ")
    private String major;

    public Student(String id, String name, Integer age, ArrayList<Classes> classList, String advisorName, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.classList = new ArrayList<>();
        this.advisorName = advisorName;
        this.major = major;
    }
}
