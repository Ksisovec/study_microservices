package net.javaguides.organizationservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.organizationservice.dto.OrganizationDto;
import net.javaguides.organizationservice.entity.Organization;
import net.javaguides.organizationservice.mapper.OrganizationMapper;
import net.javaguides.organizationservice.repository.OrganizationRepository;
import net.javaguides.organizationservice.service.OrganizationService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.fromDto(organizationDto);
        Organization savedOrganisation = organizationRepository.save(organization);
        return OrganizationMapper.toDto(savedOrganisation);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String code) {
        Organization organization = organizationRepository.findByOrganizationCode(code);
        return OrganizationMapper.toDto(organization);
    }


}
