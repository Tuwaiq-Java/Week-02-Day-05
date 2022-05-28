package com.example.school.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Data
public class Teacher {
    @NotEmpty(message = "id is required ")
    private String id ;
    @NotEmpty(message = "name is required ")
    private String name;
    private ArrayList<Classes> classList= new ArrayList();
}
