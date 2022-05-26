package com.example.schoolmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class Assign {
    @NotBlank(message = "something is missing, make sure to write all the needed info")
    private String id;
    @NotBlank(message = "something is missing, make sure to write all the needed info")
    private String assinedid;
    @NotBlank(message = "something is missing, make sure to write all the needed info")
    private String classesid;
}
