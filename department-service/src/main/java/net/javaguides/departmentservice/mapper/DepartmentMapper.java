package net.javaguides.departmentservice.mapper;

import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper DEPARTMENT_MAPPER = Mappers.getMapper(DepartmentMapper.class);

    Department mapToDepartment(DepartmentDto departmentDto);

    DepartmentDto mapToDepartmentDto(Department department);
}
