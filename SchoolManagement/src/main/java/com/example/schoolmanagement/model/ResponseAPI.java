package com.example.schoolmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class ResponseAPI {
    private String message;
    private Integer status;
}
