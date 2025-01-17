package com.management.servicde.impl;

import com.management.entity.Employee;
import com.management.exception.EmployeeNotFoundException;
import com.management.exception.EmployeesDataNotPresentException;
import com.management.repository.EmployeeRepository;
import com.management.servicde.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("employee is not exist's by this name or id"));
    }

    @Override
    public Employee findByName(String name) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployee() {
        if(employeeRepository.findAll().isEmpty())
            throw new EmployeesDataNotPresentException();
        return employeeRepository.findAll();
    }

    @Override
    public Employee employeeUpdate(Employee employee) {
        return null;
    }

    @Override
    public Employee postEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(int id) {
        if(employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            return;
        }
        throw new EmployeeNotFoundException("employee is not exist's by this name or id");
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        if(!employeeRepository.existsById(id)){
            throw new EmployeeNotFoundException("Employee is not found by id");
        }
        Employee employee1 = employeeRepository.findById(id).get();
        employee1.setId(id);
        if(!employee1.getName().equals(employee.getName()) && employee.getName() != null)
            employee1.setName(employee.getName());
        if(!employee1.getEmail().equals(employee.getEmail()) && employee.getName() != null)
            employee1.setEmail(employee.getEmail());
        if(employee1.getSalary() != employee.getSalary())
            employee1.setSalary(employee.getSalary());
        if(!employee1.getHireDate().equals(employee.getHireDate()) && employee.getName() != null)
            employee1.setHireDate(employee.getHireDate());
        if(!employee1.getTitle().equals(employee.getTitle()) && employee.getName() != null)
            employee1.setTitle(employee.getTitle());
        if(employee1.isPayment() != employee.isPayment()){
            employee1.setPayment(employee.isPayment());
        }
        return employeeRepository.save(employee1);
    }
}
