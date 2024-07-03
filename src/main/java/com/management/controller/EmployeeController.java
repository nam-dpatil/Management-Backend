package com.management.controller;

import com.management.entity.Employee;
import com.management.exception.EmployeeNotFoundException;
import com.management.exception.EmployeesDataNotPresentException;
import com.management.servicde.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class EmployeeController {
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);
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
                logger.error(e + "Employee is not present by id");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        try{
            return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.ACCEPTED);
        }
        catch (EmployeesDataNotPresentException e) {
            e.printStackTrace();
            logger.error(e + "Employees is not present in db");
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
            logger.error(e + "Employee is not present which is you deleting");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        try{
            return new ResponseEntity<>(employeeService.updateEmployee(id, employee), HttpStatus.ACCEPTED);
        }
        catch (EmployeeNotFoundException e){
            e.printStackTrace();
            logger.error(e + "Updating employee is not present");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
