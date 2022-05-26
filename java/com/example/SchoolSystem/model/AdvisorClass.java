package com.example.SchoolSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
public class AdvisorClass {
    @NotEmpty(message = "id is required")
    private String id;
    @NotEmpty(message = "name is required")
    private String name;
    @NotNull(message = "age is required")
    private Integer age;
    @NotEmpty(message = "Advisor is required")
    private ArrayList advisor;

    public AdvisorClass(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.advisor = new ArrayList<>();
    }
}
