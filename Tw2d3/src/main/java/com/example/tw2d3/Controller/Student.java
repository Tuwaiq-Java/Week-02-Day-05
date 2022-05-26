package com.example.tw2d3.Controller;
import lombok.*;
import javax.validation.constraints.*;
@AllArgsConstructor @Data
public class Student extends Object {
    @NotEmpty(message = "name is required")
    private String name;
    @NotEmpty(message = "id is required")
    @Size(min = 10,max = 10 ,message = "id must be 10 number")
    private String id;
    @Min(value = 20,message = "age must be more than or equal 20")
   @NotNull (message = "major is required")
    @Positive
    private Integer age;
    @NotEmpty(message = "major is required")
    private String major;
    @NotEmpty(message = "email is required")
    @Email (message = "error email")
    private String email;
}
