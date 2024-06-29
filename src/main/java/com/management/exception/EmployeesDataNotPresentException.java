package com.management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployeesDataNotPresentException extends RuntimeException{
    public EmployeesDataNotPresentException(String message) {
        super(message);
    }
}
