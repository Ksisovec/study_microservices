package com.example.employeeservice.controller;

import com.example.employeeservice.client.DepartmentClient;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.dto.FullEmployeeInfo;
import com.example.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("save")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<FullEmployeeInfo>> getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
