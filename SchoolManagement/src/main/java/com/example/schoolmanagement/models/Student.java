package com.example.schoolmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
public class Student {
    @NotNull @Size(min=8, max = 10)
    private String id;
    @NotNull
    private String name;
    @NotNull @PositiveOrZero
    private Integer age;

    private ArrayList<Classes> classList;
    @NotEmpty
    private String advisorName;
    @NotEmpty
    private String major;

    public Student(String id, String name, Integer age, String advisorName, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.advisorName = advisorName;
        this.major = major;
        this.classList = new ArrayList<>();
    }
    public Boolean addClass(Classes c){
        return this.classList.add(c);
    }
}
