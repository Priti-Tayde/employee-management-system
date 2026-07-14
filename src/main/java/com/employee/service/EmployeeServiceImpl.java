package com.employee.service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import com.employee.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.employee.dto.EmployeeDto;
import org.modelmapper.ModelMapper;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                           ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
         this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

   @Override
   public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

       Employee employee = mapToEntity(employeeDto);

       Employee savedEmployee = employeeRepository.save(employee);

       return mapToDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Employee not found with id: " + id));

        return mapToDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {

        Employee employee = mapToEntity(employeeDto);

        Employee updatedEmployee = employeeRepository.save(employee);

        return mapToDto(updatedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long id) {

        employeeRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Employee not found with id: " + id));

        employeeRepository.deleteById(id);
    }  
    
    @Override
    public Page<EmployeeDto> getAllEmployees(int pageNo, int pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Employee> employees = employeeRepository.findAll(pageable);

        return employees.map(this::mapToDto);
    }

    @Override
    public List<EmployeeDto> searchEmployees(String keyword) {

        List<Employee> employees =
                employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword);

        return employees.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private EmployeeDto mapToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    private Employee mapToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }
}
