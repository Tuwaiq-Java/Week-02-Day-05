package com.example.springday05.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor @Data
public class Classes {
    @NotEmpty (message = "id is required")
    private String id;

    @NotEmpty (message = "name is required")
    private String name;



}
