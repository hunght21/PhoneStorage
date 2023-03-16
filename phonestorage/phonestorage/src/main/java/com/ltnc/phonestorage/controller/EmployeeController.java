package com.ltnc.phonestorage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ltnc.phonestorage.entity.Employee;
import com.ltnc.phonestorage.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/employees/new")
    public String createEmployeeForm(Model model) {

        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "create_employee";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable(value = "id") Integer employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        return "edit_employee";

    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable(value = "id") Integer employeeId,
                                 @ModelAttribute("employee") Employee employee,
                                 Model model) {
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);
        existingEmployee.setEmployeeId(employeeId);
        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setEmployeeEmail(employee.getEmployeeEmail());

        employeeService.updateEmployee(existingEmployee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Integer employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return "redirect:/employees";
    }
}
