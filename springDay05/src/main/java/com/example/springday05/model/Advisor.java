package com.example.springday05.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;

@AllArgsConstructor @Data
public class Advisor {
    @NotEmpty (message = "id is required")
    private String id;

    @NotEmpty (message = "name is required")
    private String name;

    @NotNull(message = "age is required")
    @Positive
    private int age;

}
