package com.example.excercise9.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor @Data
public class Course {

    @NotEmpty(message = "Id is required")
    private String id;

    @NotEmpty(message = "Name is required")
    private String name;

}
