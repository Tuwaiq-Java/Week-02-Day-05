package com.example.school.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Advisor {
    @NotNull
    @Size(min = 2, message = "ID must be more than 2 digit")
    private String advisortId;

    @NotNull(message = "Name is required !")
    @Size(min = 3, message = "ID must be more than 2 character")
    private String advisorName ;

    @NotNull(message = "Age is required !")
    @Min(value = 5, message = "Age can't be less than 5 years")
    private Integer age;

    @NotNull(message = "Classes is required !")
    private ArrayList<Classes > classes;

}
