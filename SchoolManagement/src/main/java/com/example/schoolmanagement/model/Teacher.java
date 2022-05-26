package com.example.schoolmanagement.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Data
public class Teacher {
    @NotEmpty(message = "ID is required!")
    private String ID;
    @NotEmpty(message = "name is required!")
    private String name;
    private ArrayList<Class> classList;

    public Teacher(String ID, String name, ArrayList<Class> classList) {
        this.ID = ID;
        this.name = name;
        this.classList = new ArrayList<>();
    }
}
