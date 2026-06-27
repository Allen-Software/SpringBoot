package com.example.User_Management_System.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.User_Management_System.model.Employee;
import com.example.User_Management_System.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    void testSaveEmployee_Controller_Success() throws Exception {
        mockMvc.perform(post("/employee/save")
                .param("name", "控Controller")
                .param("gmail", "controller@gmail.com")
                .param("department", "HR")
                .param("gender", "女"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(employeeService, times(1)).saveEmployee(any(Employee.class));
    }

    @Test
    void testDeleteEmployee_Controller_Success() throws Exception {
        mockMvc.perform(post("/employee/delete")
                .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(employeeService, times(1)).deleteEmployeeById(1L);
    }

    @Test
    void testUpdateName_Controller_Success() throws Exception {
        Employee existingEmployee = new Employee();
        existingEmployee.setId(1L);
        existingEmployee.setName("舊姓名");

        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(existingEmployee));
        
        mockMvc.perform(post("/employee/update-name")
                .param("id", "1")
                .param("name", "新姓名"))
                .andExpect(status().isOk())
                // 驗證是否正確回傳 Thymeleaf 片段
                .andExpect(view().name("html/update :: alertMessage"))
                .andExpect(model().attribute("message", "姓名更新成功！"));

        verify(employeeService, times(1)).saveEmployee(any(Employee.class));
    }

    @Test
    void testUpdateName_Controller_EmployeeNotFound() throws Exception {
        // 模擬 Service：找不到員工
        when(employeeService.getEmployeeById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(post("/employee/update-name")
                .param("id", "999")
                .param("name", "新姓名"))
                .andExpect(status().isOk())
                .andExpect(view().name("html/update :: alertMessage"))
                .andExpect(model().attribute("message", "找不到該員工編號（員工不存在）"));
    }

    @Test
    void testUpdateName_Controller_SameName() throws Exception {
        Employee existingEmployee = new Employee();
        existingEmployee.setId(1L);
        existingEmployee.setName("相同姓名");

        // 模擬 Service：新舊姓名一模一樣
        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(existingEmployee));

        mockMvc.perform(post("/employee/update-name")
                .param("id", "1")
                .param("name", "相同姓名"))
                .andExpect(status().isOk())
                .andExpect(view().name("html/update :: alertMessage"))
                .andExpect(model().attribute("message", "新姓名與目前資料庫中的舊姓名相同，無需更新"));
    }
}