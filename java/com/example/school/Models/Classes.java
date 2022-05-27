package com.example.school.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Classes {

    @NotNull
    @Size(min = 2, message = "ID must be more than 2 digit")
    private String classId;

    @NotNull(message = "Name is required !")
    @Size(min = 3, message = "ClassName must be more than 2 character")
    private String className ;






}
