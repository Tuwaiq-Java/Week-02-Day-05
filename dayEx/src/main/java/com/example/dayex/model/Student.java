package com.example.dayex.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
@Data
public class Student {
    @NotEmpty(message = "ID cannot be empty")
    private String id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Age cannot be empty")
    private Integer age;
    private ArrayList<Classes> classList;
    @NotEmpty(message = "Advisor Name cannot be empty")
    private String advisorName;
    @NotEmpty(message = "Major cannot be empty")
    private String major;

    public Student(String id, String name, Integer age, ArrayList<Classes> classList, String advisorName, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        classList = new ArrayList<>();
        this.advisorName = advisorName;
        this.major = major;
    }
}
