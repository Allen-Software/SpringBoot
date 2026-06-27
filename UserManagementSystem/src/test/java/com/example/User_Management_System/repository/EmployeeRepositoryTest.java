package com.example.User_Management_System.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.User_Management_System.model.Employee;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testSaveEmployee_Repository_Success() {
        Employee employee = new Employee();
        employee.setName("雷repository");
        employee.setGmail("repo@gmail.com");
        employee.setDepartment("IT");
        employee.setGender("男");

        Employee savedEmployee = employeeRepository.save(employee);

        assertNotNull(savedEmployee.getId(), "儲存成功後，ID 不應該為空！");
        assertEquals("雷repository", savedEmployee.getName());
    }

    @Test
    void testDeleteEmployee_Repository_Success() {
        Employee employee = new Employee();
        employee.setName("待刪除員工");
        Employee saved = employeeRepository.save(employee);
        Long id = saved.getId();

        employeeRepository.deleteById(id);

        boolean exists = employeeRepository.existsById(id);
        org.junit.jupiter.api.Assertions.assertFalse(exists, "資料應該要被刪除了！");
    }

    @Test
    void testFindEmployeeById_Repository_Success() {
        Employee employee = new Employee();
        employee.setName("查詢測試員");
        Employee saved = employeeRepository.save(employee);

        Optional<Employee> found = employeeRepository.findById(saved.getId());

        assertTrue(found.isPresent(), "應該要找得到該名員工！");
        assertEquals("查詢測試員", found.get().getName());
    }
}