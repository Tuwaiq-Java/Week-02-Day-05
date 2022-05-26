package com.example.excercise9.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

 @Data
public class Teacher {

    @NotEmpty(message = "Id is required")
    private String id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Course is required")
    private ArrayList<Course> courseList ;


     public Teacher(String id, String name, ArrayList<Course> courseList) {
         this.id = id;
         this.name = name;
         this.courseList = new ArrayList<>();
     }

     public String getCoursesID(Teacher teacher) {

         return teacher.getId();
     }
 }
