package com.example.schoolmanagementsoftware.moles;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class Advisor {

    @NotEmpty(message = "id is required")
    private String id;
    @NotEmpty(message = "name is required")
    private String name;
    @NotNull(message = "age is required")
    private Integer age;



}
