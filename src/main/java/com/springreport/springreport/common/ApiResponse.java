package com.springreport.springreport.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse{
    private int status;
    private String message;

}
