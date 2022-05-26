package com.example.SchoolSystem.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.SplittableRandom;

@Data
public class TeacherClass {
    @NotEmpty(message = "id is required")
    private String id;
    @NotEmpty(message = "name is required")
    private String name;
    @NotEmpty(message = "Class List is required")
    private ArrayList classList;

    public TeacherClass(String id, String name) {
        this.id = id;
        this.name = name;
        this.classList = new ArrayList<>();
    }
}
