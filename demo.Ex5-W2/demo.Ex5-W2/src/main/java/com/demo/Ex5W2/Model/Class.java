package com.demo.Ex5W2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@AllArgsConstructor @Data
public class Class {
    @NotEmpty(message = "ClassID is required")
    private String ClassID;

    @Pattern(regexp = "(?=.[a-z])(?=.[A-Z])", message = "Please enter only letters!")
    @NotEmpty(message = "ClassName is required")
    private String ClassName;
}
