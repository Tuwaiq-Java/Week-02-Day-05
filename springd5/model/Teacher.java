package com.example.springd5.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@AllArgsConstructor
@Data
public class Teacher {
    @NotEmpty(message = "must insert teacher ID")
    private String id;

    @NotEmpty(message = "must insert teacher name")
    private String name;

   // @NotEmpty(message = "must insert your classes name")
    private ArrayList<Classes> classList;

}
