package com.demo.Ex5W2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;

@AllArgsConstructor @Data
public class Teacher {
    @NotEmpty(message = "TeacherID is required")
    private String TeacherID;

    @Pattern(regexp = "(?=.[a-z])(?=.[A-Z])", message = "Please enter only letters!")
    @NotEmpty(message = "TeacherName is required")
    private String TeacherName;

    @NotEmpty(message = "classList is required")
    private ArrayList<Class> classList;
}
