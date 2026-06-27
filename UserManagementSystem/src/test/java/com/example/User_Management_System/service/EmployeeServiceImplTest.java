package com.example.User_Management_System.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.User_Management_System.model.Employee;
import com.example.User_Management_System.repository.EmployeeRepository;
import com.example.User_Management_System.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void testSaveEmployee_Service_Success() {
        Employee employee = new Employee();
        employee.setName("莫Service");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        assertDoesNotThrow(() -> employeeService.saveEmployee(employee));

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testDeleteEmployee_Service_Success() {
        Long empId = 1L;

        assertDoesNotThrow(() -> employeeService.deleteEmployeeById(empId));

        verify(employeeRepository, times(1)).deleteById(empId);
    }

    @Test
    void testGetEmployeeById_Service_Success() {
        Long empId = 1L;
        Employee employee = new Employee();
        employee.setId(empId);
        employee.setName("查Service");

        when(employeeRepository.findById(empId)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(empId);

        assertTrue(result.isPresent());
        assertEquals("查Service", result.get().getName());
        verify(employeeRepository, times(1)).findById(empId);
    }
}
