package com.example.springd5.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@AllArgsConstructor
@Data
public class Student {

   // ID , name , age ,classList < Arraylist > , advisorName , major ( all should not be empty )
    @NotEmpty(message = "must insert your student ID")
    @Size(min = 3,message = "ID should be 3 ")
    private String id;

    @NotEmpty(message = "must insert your student name")
    private String name;

    @NotNull(message = "age can not be null")
    private Integer age;

   // @NotEmpty(message = "must insert your classes name")
    private ArrayList<Classes> classList;

    @NotEmpty(message = "Student must have advisor")
    private String advisor;

    @NotEmpty(message = "must insert major")
    private String major;

}

