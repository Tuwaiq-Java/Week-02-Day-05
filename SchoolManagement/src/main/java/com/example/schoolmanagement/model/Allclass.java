package com.example.schoolmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@AllArgsConstructor
@Data
public class Allclass {
    @NotEmpty(message = " id is required")
    private String id;
    @NotEmpty(message = "name is required")
    private String name;



}
