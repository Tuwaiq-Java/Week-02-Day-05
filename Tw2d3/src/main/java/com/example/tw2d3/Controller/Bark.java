package com.example.tw2d3.Controller;

import lombok.*;
import javax.validation.constraints.*;
@AllArgsConstructor
@Data
public class Bark extends Object{
    @NotNull(message = "id is required")
    @Size(min = 3,message = "id must be more than 2 number")
   private String rideID;
    @NotNull (message = "name is required")
    @Size(min = 4,message = "name must be more than 3 number")
   private String rideName;
    @NotNull (message = "type is required")
    @Pattern(regexp = "(Rollercoaster|thriller|water)",message = "ride type must be Rollercoaster or thriller or water")
   private String rideType;
    @NotNull (message = "ticket is required")
    @Digits( integer =3, fraction = 0,message = "tickets must be number")
   private Integer tickets;
    @NotNull (message = "priceis required")
    @Digits( integer =3, fraction = 0,message = "price must be number")
   private Integer price;



}
