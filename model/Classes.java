package com.example.schoolmanagementsoftware.moles;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Data
public class Classes {

    @NotEmpty(message = "id is required")
    private String id;
    @NotEmpty(message = "name is required")
    private String name;

}
