package com.example.employeeservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    @NotBlank(message = "Department name should not be empty or null")
    private String departmentName;
    private String departmentDescription;
    @NotBlank(message = "Department code should not be empty or null")
    private String departmentCode;
}
