package com.example.schoolmanagement.model;
import lombok.*;
import javax.validation.constraints.*;
import java.util.ArrayList;

@AllArgsConstructor
@Data
public class Student {
    @NotBlank
    private String  id;
    @NotBlank
    private String  name;
    @NotNull
    private Integer  age;
    @NotNull
    private ArrayList<Classes> classList;
    @NotBlank
    private String advisorName;
    @NotBlank
    private String  major;

}
