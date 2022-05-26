package com.example.schoolmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data @AllArgsConstructor
public class Advisor {
    @NotNull @Size(min=8, max = 10)
    private String id;
    @NotNull
    private String name;
    @NotNull @PositiveOrZero
    private Integer age;

    private ArrayList<Classes> classList;

    public Advisor(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.classList = new ArrayList<>();
    }
    public Boolean addClass(Classes c){
        return this.classList.add(c);
    }
}
