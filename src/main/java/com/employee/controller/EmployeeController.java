package com.employee.controller;

import com.employee.dto.EmployeeDto;
import com.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get all employees
    @GetMapping
    public Page<EmployeeDto> getAllEmployees(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        return employeeService.getAllEmployees(pageNo, pageSize, sortBy);
    }

    // Create employee
    @PostMapping
    public EmployeeDto saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return employeeService.saveEmployee(employeeDto);
    }

    // Get employee by id
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // Search employees
    @GetMapping("/search")
    public List<EmployeeDto> searchEmployees(@RequestParam String keyword) {
        return employeeService.searchEmployees(keyword);
    }

    // Update employee
    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id,
                                   @Valid @RequestBody EmployeeDto employeeDto) {
        employeeDto.setId(id);
        return employeeService.updateEmployee(employeeDto);
    }

    // Delete employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return "Employee deleted successfully!";
    }
}