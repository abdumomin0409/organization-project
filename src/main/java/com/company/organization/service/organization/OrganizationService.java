package com.company.organization.service.organization;

import com.company.organization.domain.organization.Organization;
import com.company.organization.exception_handler.exception.ValidateException;
import com.company.organization.payload.organization.OrganizationCreateDTO;
import com.company.organization.payload.organization.OrganizationUpdateDTO;
import com.company.organization.repository.organization.OrganizationRepository;
import com.company.organization.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.company.organization.mapper.organization.OrganizationMapper.ORGANIZATION_MAPPER;

@Service
@RequiredArgsConstructor
public class OrganizationService implements BaseService {
    private final OrganizationRepository organizationRepository;
    Logger logger = Logger.getLogger(OrganizationService.class.getName());

    public Organization create(OrganizationCreateDTO dto) {
        logger.log(Level.INFO, "OrganizationService create method called");
        if (existsByName(dto.name()))
            throw new ValidateException("Organization already exists", -1400002);
        Organization organization = ORGANIZATION_MAPPER.fromCreateToOrganization(dto);
        organization.setIsActive(true);
        return organizationRepository.save(organization);
    }

    public Organization updateById(Long id, OrganizationUpdateDTO dto) {
        logger.log(Level.INFO, "OrganizationService updateById method called");
        if (!existsById(id))
            throw new ValidateException("Organization not found", -1400001);
        Organization byId = getById(id);
        ORGANIZATION_MAPPER.fromUpdateToOrganization(dto, byId);
        return organizationRepository.save(byId);
    }

    public Organization deleteById(Long id) {
        logger.log(Level.INFO, "OrganizationService deleteById method called");
        if (!existsById(id))
            throw new ValidateException("Organization not found", -1400001);
        Organization organization = getById(id);
        organization.setIsActive(false);
        return organizationRepository.save(organization);
    }

    public Organization getById(Long id) {
        logger.log(Level.INFO, "OrganizationService getById method called");
        return organizationRepository.findById(id).orElseThrow(() -> new ValidateException("Organization not found", -1400005));
    }

    public List<Organization> getAll() {
        logger.log(Level.INFO, "OrganizationService getAll method called");
        return organizationRepository.findAll();
    }

    public Page<Organization> getAllFixed(Pageable pageable) {
        logger.log(Level.INFO, "OrganizationService getAllFixed method called");
        return organizationRepository.findAllByPageable(pageable);
    }

    public boolean existsByName(String name) {
        logger.log(Level.INFO, "OrganizationService existsByName method called");
        return organizationRepository.existsByName(name);
    }

    public boolean existsById(Long id) {
        logger.log(Level.INFO, "OrganizationService existsById method called");
        return organizationRepository.existsById(id);
    }


}
