package com.example.schoolmanagementsoftware.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
public class Advisor {

    @NotEmpty(message = "Id cannot be empty")
    @Size(min = 2)
    private String id;

    @NotEmpty(message = "Id cannot be empty")
    @Size(min = 2)
    private String name;

    @NotNull(message = "age cannot be empty")
    @Min(value=25 , message = "age cannot be less than 25")
    private Integer age;

    public Advisor(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
