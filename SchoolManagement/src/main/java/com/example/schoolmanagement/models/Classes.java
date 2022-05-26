package com.example.schoolmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data @AllArgsConstructor
public class Classes {
    @NotNull @Size(min=3, max = 5)
    private String id;
    @NotNull
    private String name;
}
