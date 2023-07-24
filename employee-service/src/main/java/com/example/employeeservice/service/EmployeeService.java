package com.example.employeeservice.service;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employee);

    EmployeeDto getEmployeeById(Long id);
}
