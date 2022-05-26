package com.example.springday05.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;

@Data
public class Teacher {
    @NotEmpty (message = "id is required")
    private String id;

    @NotEmpty (message = "name is required")
    private String name;


    private ArrayList<Classes> classList;

    public Teacher(String id, String name, ArrayList<Classes> classList) {
        this.id = id;
        this.name = name;
        this.classList = new ArrayList<>();
    }
}
