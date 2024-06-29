package com.management.servicde;

import com.management.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//@Service
public interface EmployeeService {
    Employee findById(int id);
    Employee findByName(String name);
    List<Employee> getAllEmployee();
    Employee employeeUpdate(Employee employee);
    Employee postEmployee(Employee employee);
    void deleteEmployeeById(int id);
    Employee updateEmployee(int id, Employee employee);
}
