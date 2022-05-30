package com.example.schoolmangmentsys.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor @Data
public class Advisor {
    @NotEmpty(message = " id is required")
    private String id ;
    @NotEmpty(message = " id is required")
    private String name;
    @NotEmpty(message = " id is required")
    private String age ;

}
