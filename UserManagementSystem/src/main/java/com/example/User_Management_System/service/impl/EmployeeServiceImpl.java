package com.example.User_Management_System.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.User_Management_System.model.Employee;
import com.example.User_Management_System.repository.EmployeeRepository;
import com.example.User_Management_System.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
}
