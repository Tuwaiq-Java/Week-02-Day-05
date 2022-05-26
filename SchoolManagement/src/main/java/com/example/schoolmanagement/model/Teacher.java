package com.example.schoolmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@AllArgsConstructor
@Data
public class Teacher {
    @NotBlank
    private String  id;
    @NotBlank
    private String  name;
    @NotNull
    private ArrayList<Classes> classList;
}
