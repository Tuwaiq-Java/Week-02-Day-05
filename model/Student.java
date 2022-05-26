package com.example.schoolmanagementsoftware.moles;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@AllArgsConstructor
@Data
public class Student {

    @NotEmpty(message = "id is required")
    private String id;
    @NotEmpty(message = "name is required")
    private String name;
    private ArrayList<Classes> classArrayList;
    @NotEmpty(message = "advisorName is required")
    private String advisorName;
    @NotEmpty(message = "major is required")
    private String major;

}
