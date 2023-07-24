package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exceptions.EmailAlreadyExistException;
import com.example.employeeservice.exceptions.ResourceNotFoundException;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.employeeservice.mapper.EmployeeMapper.EMPLOYEE_MAPPER;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employeeDto.getEmail());
        if (employeeOptional.isPresent()) {
            throw new EmailAlreadyExistException("Email already exist");
        }

        Employee employee = employeeRepository.save(EMPLOYEE_MAPPER.mapToEmployee(employeeDto));
        return EMPLOYEE_MAPPER.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(EMPLOYEE_MAPPER::mapToEmployeeDto)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", id.toString()));
    }
}