package com.example.tw2d3.Controller2;
import lombok.*;
import javax.validation.constraints.*;
@AllArgsConstructor
@Data
public class Employees extends Object{

    @NotNull(message = "id is required")
    @Size(min = 3,message = "id must be more than 2 number")
    private String ID;
    @NotNull(message = "name is required")
    @Size(min = 5,message = "name must be more than 4 number")
    private String name;
    @NotNull(message = "age is required")
    @Digits( integer =3, fraction = 0,message = "age must be number")
    @Min(value = 26,message = "age must be more than 25")
    private Integer age;
    //@Pattern(regexp = "(false)",message = "on leave must be false")
    private boolean onLeave;
    @NotNull(message = "year is required")
    @Digits( integer =4, fraction = 0,message = "year must be number")
    //@Size(min = 4,max = 4,message = "year must be 4 number")
    private Integer employmentYear;
    @Digits( integer =3, fraction = 0,message = "annual leave days  must be number")
    @NotNull (message = "annual Leave days is required")
    private Integer annualLeave;

}
