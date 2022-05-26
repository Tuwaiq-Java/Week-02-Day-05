package com.example.schoolmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Data
public class Class {
    @NotEmpty(message = "ID is required!")
    private String ID;
    @NotEmpty(message = "name is required!")
    private String name;

}
