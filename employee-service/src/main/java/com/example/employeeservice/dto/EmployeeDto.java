package com.example.employeeservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    @NotBlank(message = "First name should not be empty or null")
    private String firstName;
    @NotBlank(message = "Last name should not be empty or null")
    private String lastName;
    @NotBlank(message = "Email name should not be empty or null")
    @Email(message = "Email should be valid")
    private String email;
}
