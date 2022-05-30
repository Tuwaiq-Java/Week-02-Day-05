package com.example.schoolmangmentsys.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@AllArgsConstructor @Data
public class Student {

    @NotEmpty(message = "id is required")
    private String id ;
    @NotEmpty(message = " name is required")
    @Size(min = 3, message = "name must be more tha 3 char ")
    private String name ;
    @NotEmpty(message = " age is required")
    private String age ;

    private ArrayList<String> classList;

    @NotEmpty(message = " advisor name is required")
    private String advisorName;
    @NotEmpty(message = " major is required")
    @Size(min = 4, message = "major must be more tha 4 char ")
    private String major;

}
