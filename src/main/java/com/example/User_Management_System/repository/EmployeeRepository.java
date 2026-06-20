package com.example.User_Management_System.repository;

import com.example.User_Management_System.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}