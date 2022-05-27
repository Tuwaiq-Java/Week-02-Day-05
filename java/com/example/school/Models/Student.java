package com.example.school.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
public class Student {
    @NotNull
    @Size(min = 2, message = "ID must be more than 2 digit")
   private String studentId;
    @NotNull(message = "Name is required !")
    @Size(min = 3, message = "studentName must be more than 2 character")
    private String studentName ;
    @NotNull(message = "Age is required !")
    @Min(value = 5, message = "Age can't be less than 5 years")
   private Integer age;
   private ArrayList<Classes > classes;
    @NotNull(message = "Advisor is required ")
   private String advisorName;
    @NotNull(message = "Major is required ")
   private String major;

    public Student(String studentId, String studentName, Integer age,
                   ArrayList<Classes> classes, String advisorName, String major) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.classes = new ArrayList<>();
        this.advisorName = advisorName;
        this.major = major;
    }


    public Student StudentByid(String classid) {
        for (Classes classe:classes ) {
            if (classe.getClassId().equals(classid))
                return this;

        }
        return null;
    }

}
