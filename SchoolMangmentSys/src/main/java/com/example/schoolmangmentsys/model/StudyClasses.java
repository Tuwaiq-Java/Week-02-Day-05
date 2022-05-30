package com.example.schoolmangmentsys.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor @Data
public class StudyClasses {
    @NotEmpty(message = "id is required")
    private String id ;
    @NotEmpty(message = "name is requird")
    private String name;
}
