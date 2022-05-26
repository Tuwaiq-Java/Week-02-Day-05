package com.example.schoolmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@Data
public class Classes {
    @NotBlank
    private String  id;
    @NotBlank
    private String  name;
}
