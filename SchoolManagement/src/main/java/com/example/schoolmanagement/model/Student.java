package com.example.schoolmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
@Data
public class Student {

    @NotEmpty(message = " id is required")
    private String id;
    @NotEmpty(message = "name is required")
    private String name;
    @NotNull(message = "age is required")
    private  Integer age;

    private ArrayList<Allclass> sclasslist;
    @NotEmpty(message = " advisor name is required")
    private String advisorname;
    @NotEmpty(message = " major name is required")
    private String major;

    public Student(String id, String name, Integer age,  String advisorname, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sclasslist = new ArrayList<>();
        this.advisorname = advisorname;
        this.major = major;
    }
}
