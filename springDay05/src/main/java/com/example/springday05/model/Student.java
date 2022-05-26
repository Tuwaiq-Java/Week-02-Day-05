package com.example.springday05.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;

@Data
public class Student {
    @NotEmpty (message = "id is required")
    private String id;

    @NotEmpty (message = "name is required")
    private String name;

    @NotNull(message = "age is required")
    @Positive
    private int age;


    private ArrayList<Classes> classList;

    @NotEmpty (message = "advisorName is required")
    private String advisorName;

    @NotEmpty (message = "major is required")
    private String major;

    public Student(String id, String name, int age, ArrayList<Classes> classList, String advisorName, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.classList = new ArrayList<>();
        this.advisorName = advisorName;
        this.major = major;
    }
}
