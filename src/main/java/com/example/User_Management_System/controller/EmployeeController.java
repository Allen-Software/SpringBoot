package com.example.User_Management_System.controller;

import com.example.User_Management_System.model.Employee;
import com.example.User_Management_System.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String showAddPage(Model model) {
        model.addAttribute("employee", new Employee());
        return "html/add";
    }

    @GetMapping("/delete")
    public String showDeletePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "html/delete";
    }

    @GetMapping("/update")
    public String showUpdatePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "html/update";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteEmployeeById(@ModelAttribute("employee") Employee employee) {
        employeeService.deleteEmployeeById(employee.getId());
        return "redirect:/";
    }

    @PostMapping("/update-name")
    public String updateName(@ModelAttribute("employee") Employee employee, Model model) {
        // 1. 檢查是否存在
        java.util.Optional<Employee> existingEmp = employeeService.getEmployeeById(employee.getId()); // 假設你的 service 有此方法
        
        if (existingEmp.isEmpty()) {
            model.addAttribute("message", "找不到該員工編號（員工不存在）");
            return "html/update :: alertMessage";
        }

        // 2. 檢查新舊姓名是否相同
        Employee dbEmployee = existingEmp.get();
        if (dbEmployee.getName() != null && dbEmployee.getName().equals(employee.getName())) {
            model.addAttribute("message", "新姓名與目前資料庫中的舊姓名相同，無需更新");
            return "html/update :: alertMessage";
        }

        // 3. 通過驗證，執行更新
        dbEmployee.setName(employee.getName());
        employeeService.saveEmployee(dbEmployee);
        
        model.addAttribute("message", "姓名更新成功！");
        return "html/update :: alertMessage";
    }
}