package com.management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String msg){

    }
}
