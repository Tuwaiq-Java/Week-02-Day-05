package com.example.SchoolSystem.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Data
public class StudentClass {
    @NotEmpty(message = "id is required")
    private String id;
    @NotEmpty(message = "id is required")
    private String name;
    @NotEmpty(message = "age is required")
    private Integer age;
    @NotEmpty(message = "class list is required")
    private ArrayList classList;
    @NotEmpty(message = "advisor name is required")
    private String advisorName;
    @NotEmpty(message = "major is required")
    private String major;

    public StudentClass(String id, String name, Integer age, String advisorName, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.classList = new ArrayList<>();
        this.advisorName = advisorName;
        this.major = major;
    }
}
