package com.example.schoolmanagement.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
public class Student {
    @NotEmpty(message = "ID is required!")
    private String ID;
    @NotEmpty(message = "name is required!")
    private String name;
    @NotNull(message = "age is required!")
    private Integer age;
    private ArrayList<Class> classList;
    @NotEmpty(message = "advisorName is required!")
    private String advisorName;
    @NotEmpty(message = "major is required!")
    private String major;

    public Student(String ID, String name, Integer age, ArrayList<Class> classList, String advisorName, String major) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.classList = new ArrayList<>();
        this.advisorName = advisorName;
        this.major = major;
    }
}
