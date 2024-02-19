package net.javaguides.organizationservice.mapper;

import net.javaguides.organizationservice.dto.OrganizationDto;
import net.javaguides.organizationservice.entity.Organization;

public class OrganizationMapper {

    public static Organization fromDto(OrganizationDto dto) {
        return new Organization(
                dto.getId(),
                dto.getOrganizationName(),
                dto.getOrganizationDescription(),
                dto.getOrganizationCode(),
                dto.getCreatedDate()
        );
    }

    public static OrganizationDto toDto(Organization organization) {
        return new OrganizationDto(
                organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationCode(),
                organization.getOrganizationDescription(),
                organization.getCreatedDate());

    }
}
