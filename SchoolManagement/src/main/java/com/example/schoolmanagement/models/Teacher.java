package com.example.schoolmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data @AllArgsConstructor
public class Teacher {
    @NotNull @Size(min=8, max = 10)
    private String id;
    @NotNull
    private String name;

    private ArrayList<Classes> classList;

    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
        this.classList = new ArrayList<>();
    }
    public Boolean addClass(Classes c){
        return this.classList.add(c);
    }
}
