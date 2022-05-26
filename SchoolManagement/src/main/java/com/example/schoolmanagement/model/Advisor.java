package com.example.schoolmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;


@Data
public class Advisor {
    @NotEmpty(message = " id is required")
    private String id;
    @NotEmpty(message = "name is required")
    private String name;

    private ArrayList<Allclass> aclasslist;

    public Advisor(String id, String name) {
        this.id = id;
        this.name = name;
        this.aclasslist = new ArrayList<>();
    }
}
