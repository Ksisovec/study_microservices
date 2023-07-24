package net.javaguides.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

import static net.javaguides.departmentservice.mapper.DepartmentMapper.DEPARTMENT_MAPPER;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = departmentRepository.save(DEPARTMENT_MAPPER.mapToDepartment(departmentDto));
        return DEPARTMENT_MAPPER.mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        return DEPARTMENT_MAPPER.mapToDepartmentDto(departmentRepository.findByDepartmentCode(code));
    }
}
