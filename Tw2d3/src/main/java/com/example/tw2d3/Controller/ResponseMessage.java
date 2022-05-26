package com.example.tw2d3.Controller;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseMessage {
    private String message;
    private Integer status;

}
