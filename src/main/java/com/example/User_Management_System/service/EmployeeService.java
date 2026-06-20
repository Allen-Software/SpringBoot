package com.example.User_Management_System.service;

import java.util.Optional;

import com.example.User_Management_System.model.Employee;

public interface EmployeeService {
    void saveEmployee(Employee employee);
    void deleteEmployeeById(Long id);
    Optional<Employee> getEmployeeById(Long id);
}