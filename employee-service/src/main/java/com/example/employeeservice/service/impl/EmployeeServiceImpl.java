package com.example.employeeservice.service.impl;

import com.example.employeeservice.client.DepartmentClient;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.dto.FullEmployeeInfo;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exceptions.EmailAlreadyExistException;
import com.example.employeeservice.exceptions.ResourceNotFoundException;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static com.example.employeeservice.mapper.EmployeeMapper.EMPLOYEE_MAPPER;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentClient departmentClient;

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
    public Mono<FullEmployeeInfo> getEmployeeById(Long id) {
//        TODO: make findById reactive also (and than change map to flatmap and orElseThrow to switchIfEmpty)
        return employeeRepository.findById(id)
                .map(employee -> {
                            Mono<DepartmentDto> department = departmentClient.getDepartmentByCode(employee.getDepartmentCode());
                            return department.map(departmentDto -> new FullEmployeeInfo(EMPLOYEE_MAPPER.mapToEmployeeDto(employee), departmentDto));
                        }
                )
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id.toString()));
    }
}