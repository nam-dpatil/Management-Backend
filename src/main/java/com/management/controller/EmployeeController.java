package com.management.controller;

import com.management.entity.Employee;
import com.management.exception.EmployeeNotFoundException;
import com.management.exception.EmployeesDataNotPresentException;
import com.management.servicde.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/")
    public ResponseEntity<Employee> postEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.postEmployee(employee), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        try{
            return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
        }
        catch (EmployeeNotFoundException e){
                e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        try{
            return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.ACCEPTED);
        }
        catch (EmployeesDataNotPresentException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id){
        try{
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EmployeeNotFoundException e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        try{
            return new ResponseEntity<>(employeeService.updateEmployee(id, employee), HttpStatus.ACCEPTED);
        }
        catch (EmployeeNotFoundException e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
