package com.example.schoolmanagementsoftware.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class Course {

    @NotEmpty(message = "Id cannot be empty")
    @Size(min = 2)
    private String id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2)
    private String name;
}
