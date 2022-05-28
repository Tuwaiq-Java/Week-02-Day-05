package com.example.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor @Data
public class Advisor {
    @NotEmpty(message = "id is required ")
    private String id ;
    @NotEmpty(message = "name is required ")
    private String name;
    @NotEmpty(message = "age is required ")
    private String age;
}
