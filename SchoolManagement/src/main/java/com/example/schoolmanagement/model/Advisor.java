package com.example.schoolmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@AllArgsConstructor
@Data
public class Advisor {
    @NotBlank(message = "something is missing, make sure to write all the needed info")
    private String  id;
    @NotBlank(message = "something is missing, make sure to write all the needed info")
    private String  name;
    @NotNull(message = "something is missing, make sure to write all the needed info")
    private Integer  age;
    @NotNull
    private ArrayList<Student> studentsList;
}
