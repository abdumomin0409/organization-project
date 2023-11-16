package com.company.organization.service.organization;

import com.company.organization.domain.organization.OrganizationProduct;
import com.company.organization.exception_handler.exception.ValidateException;
import com.company.organization.payload.organization.OrganizationProductDTO;
import com.company.organization.payload.organization.OrganizationProductResponse;
import com.company.organization.repository.organization.OrganizationProductRepository;
import com.company.organization.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class OrganizationProductService implements BaseService {
    private final OrganizationProductRepository repository;
    private final OrganizationService organizationService;
    private final ProductService productService;
    Logger logger = Logger.getLogger(OrganizationProductService.class.getName());

    public ArrayList<OrganizationProductResponse> create(OrganizationProductDTO dto) {
        logger.log(Level.INFO, "OrganizationProductService create method called");
        if (!organizationService.existsById(dto.getOrganizationId()))
            throw new ValidateException("Organization not found", -1400001);
        ArrayList<OrganizationProductResponse> responses = new ArrayList<>();
        Long organizationId = dto.getOrganizationId();
        dto.getProductIdList().forEach(productId -> {
            if (!existsByOrganizationIdProductId(organizationId, productId)) {
                OrganizationProduct saved = repository.save(OrganizationProduct.organizationProductBuilder()
                        .organization(organizationService.getById(organizationId))
                        .product(productService.getById(productId))
                        .isActive(true)
                        .build());
                responses.add(OrganizationProductResponse.builder()
                        .createdAt(saved.getCreatedAt())
                        .organizationId(saved.getOrganization().getId())
                        .productId(saved.getProduct().getId())
                        .id(saved.getId())
                        .build());
            }
        });
        return responses;
    }


    public void deleteById(OrganizationProductDTO dto) {
        logger.log(Level.INFO, "OrganizationProductService deleteById method called");
        if (!organizationService.existsById(dto.getOrganizationId()))
            throw new ValidateException("Organization not found", -1400001);
        Long organizationId = dto.getOrganizationId();
        dto.getProductIdList().forEach(productId -> {
            if (existsByOrganizationIdProductId(organizationId, productId)) {
                repository.save(OrganizationProduct.organizationProductBuilder()
                        .organization(organizationService.getById(organizationId))
                        .product(productService.getById(productId))
                        .isActive(false)
                        .build());
            }
        });
    }

    public OrganizationProductResponse getById(Long id) {
        logger.log(Level.INFO, "OrganizationProductService getById method called");
        OrganizationProduct found = repository.findById(id).orElseThrow(() -> new ValidateException("Organization product not found", -1400005));
        return OrganizationProductResponse.builder()
                .createdAt(found.getCreatedAt())
                .organizationId(found.getOrganization().getId())
                .productId(found.getProduct().getId())
                .id(found.getId())
                .build();
    }

    public List<OrganizationProductResponse> getAll() {
        logger.log(Level.INFO, "OrganizationProductService getAll method called");
        ArrayList<OrganizationProductResponse> list = new ArrayList<>();
        repository.findAll().forEach(organizationProduct -> list.add(OrganizationProductResponse.builder()
                .createdAt(organizationProduct.getCreatedAt())
                .organizationId(organizationProduct.getOrganization().getId())
                .productId(organizationProduct.getProduct().getId())
                .id(organizationProduct.getId())
                .build()));
        return list;
    }

    public Page<OrganizationProduct> getAllFixed(Pageable pageable) {
        logger.log(Level.INFO, "OrganizationProductService getAllFixed method called");
        return repository.findAllByPageable(pageable);
    }

    public boolean existsByOrganizationIdProductId(Long organizationId, Long productId) {
        logger.log(Level.INFO, "OrganizationProductService existsByName method called");
        return repository.existsByOrganizationAndProduct(organizationId, productId);
    }

    public boolean existsById(Long id) {
        logger.log(Level.INFO, "OrganizationProductService existsById method called");
        return repository.existsById(id);
    }

    public OrganizationProduct findById(Long id) {
        logger.log(Level.INFO, "OrganizationProductService existsById method called");
        return repository.findById(id).orElseThrow(() -> new ValidateException("OrganizationProduct not found", -1400005));
    }

}
