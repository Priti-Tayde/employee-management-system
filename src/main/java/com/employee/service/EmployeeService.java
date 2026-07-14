package com.employee.service;

import com.employee.dto.EmployeeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);

    EmployeeDto updateEmployee(EmployeeDto employee);

    void deleteEmployeeById(Long id);

    Page<EmployeeDto> getAllEmployees(int pageNo, int pageSize, String sortBy);

    List<EmployeeDto> searchEmployees(String keyword);
}