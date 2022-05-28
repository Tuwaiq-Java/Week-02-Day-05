package com.example.school.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@RequiredArgsConstructor@Data
public class Classes {
    @NotEmpty(message = "id is required ")
    private String id ;
    @NotEmpty(message = "name is required ")
    private String name;

}
