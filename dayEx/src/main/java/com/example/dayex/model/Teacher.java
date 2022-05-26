package com.example.dayex.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
@Data
public class Teacher {
    @NotEmpty(message = "ID cannot be empty")
    private String id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    public Teacher(String id, String name, ArrayList<Classes> classList) {
        this.id = id;
        this.name = name;
        classList = new ArrayList<>();
    }

    private ArrayList<Classes> classList;

}
