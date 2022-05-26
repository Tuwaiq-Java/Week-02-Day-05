package com.example.dayex.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Advisor {
    private String id;
    private String name;
    private Integer age;
    private ArrayList classList;

    public Advisor(String id, String name, Integer age, ArrayList classList) {
        this.id = id;
        this.name = name;
        this.age = age;
        classList = new ArrayList<>();
    }
}
