package com.demo.Ex5W2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;

@AllArgsConstructor @Data
public class Student {
    @NotEmpty(message = "StudentID is required")
    private String StudentID;

    @Pattern(regexp = "(?=.[a-z])(?=.[A-Z])", message = "Please enter only letters!")
    @NotEmpty(message = "StudentName is required")
    private String StudentName;

    @NotEmpty(message = "StudentAge is required")
    private String StudentAge;

    @NotEmpty(message = "classList is required")
    private ArrayList<Class> classList;

    @NotEmpty(message = "advisorName is required")
    private String advisorName;

    @NotEmpty(message = "major is required")
    private String major;

//    public Student(String studentID, String studentName, String studentAge, String advisorName, String major) {
//        this.StudentID = studentID;
//        this.StudentName = studentName;
//        this.StudentAge = studentAge;
//        this.classList = new ArrayList<>();
//        this.advisorName = advisorName;
//        this.major = major;
//    }
}
