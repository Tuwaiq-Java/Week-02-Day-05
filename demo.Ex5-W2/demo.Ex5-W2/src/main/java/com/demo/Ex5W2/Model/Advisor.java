package com.demo.Ex5W2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;

@AllArgsConstructor @Data
public class Advisor {
    @NotEmpty(message = "AdvisorID is required")
    private String AdvisorID;

    @Pattern(regexp = "(?=.[a-z])(?=.[A-Z])", message = "Please enter only letters!")
    @NotEmpty(message = "AdvisorName is required")
    private String AdvisorName;

    @NotEmpty(message = "AdvisorAge is required")
    private String AdvisorAge;

    @NotEmpty(message = "classList is required")
    private ArrayList<Class> classList;
}
