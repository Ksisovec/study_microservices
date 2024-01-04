package com.example.employeeservice.service;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.dto.FullEmployeeInfo;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employee);

    Mono<FullEmployeeInfo> getEmployeeById(Long id);
}
