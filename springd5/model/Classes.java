package com.example.springd5.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class Classes {
    @NotEmpty(message = "must insert class ID")
    private String id;

    @NotEmpty(message = "must insert class name")
    private String name;
}
