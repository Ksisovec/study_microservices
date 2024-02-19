package net.javaguides.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "DepartmentDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    @Schema(
            description = "Department Name"
    )
    @NotBlank(message = "Department name should not be empty or null")
    private String departmentName;
    @Schema(
            description = "Department Description"
    )
    private String departmentDescription;
    @Schema(
            description = "Department Code"
    )
    @NotBlank(message = "Department code should not be empty or null")
    private String departmentCode;
}
