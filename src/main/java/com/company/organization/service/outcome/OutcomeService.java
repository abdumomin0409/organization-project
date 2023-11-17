package com.company.organization.service.outcome;

import com.company.organization.domain.branch.Warehouse;
import com.company.organization.domain.outcome.Outcome;
import com.company.organization.domain.outcome.OutcomeProduct;
import com.company.organization.payload.outcome.OutcomeDTO;
import com.company.organization.payload.outcome.OutcomeProductResponse;
import com.company.organization.payload.outcome.OutcomeResponse;
import com.company.organization.repository.outcome.OutcomeProductRepository;
import com.company.organization.repository.outcome.OutcomeRepository;
import com.company.organization.service.BaseService;
import com.company.organization.service.branch.WarehouseService;
import com.company.organization.service.organization.OrganizationProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class OutcomeService implements BaseService {
    private final OutcomeRepository outcomeRepository;
    private final OutcomeProductRepository outcomeProductRepository;
    private final WarehouseService warehouseService;
    private final OrganizationProductService organizationProductService;
    Logger logger = Logger.getLogger(OutcomeService.class.getName());

    public OutcomeResponse create(OutcomeDTO dto) {
        logger.log(Level.INFO, "BranchService create method called");
        Warehouse warehouse = warehouseService.getById(dto.getWarehouseId());

        ArrayList<OutcomeProductResponse> lists = new ArrayList<>();
        Outcome savedOutcome = outcomeRepository.save(Outcome.outcomeBuilder()
                .isActive(true)
                .warehouse(warehouse)
                .build());
        dto.getOutcomeProductDTOList().forEach(outcomeProductDTO -> {
            if (organizationProductService.existsById(outcomeProductDTO.getOrganizationProductId())) {
                OutcomeProduct outcomeProduct = getByOrganizationProduct(outcomeProductDTO.getOrganizationProductId());
                if (Objects.nonNull(outcomeProduct)) {
                    outcomeProduct.setQuantity(outcomeProduct.getQuantity() + outcomeProductDTO.getProductQuantity());
                    outcomeProduct = outcomeProductRepository.save(outcomeProduct);
                } else {
                    outcomeProduct = outcomeProductRepository.save(OutcomeProduct.builder()
                            .outcome(savedOutcome)
                            .productPrise(outcomeProductDTO.getProductPrice())
                            .quantity(outcomeProductDTO.getProductQuantity())
                            .organizationProduct(organizationProductService.findById(outcomeProductDTO.getOrganizationProductId()))
                            .build());
                }
                lists.add(OutcomeProductResponse.builder()
                        .id(outcomeProduct.getId())
                        .productPrice(outcomeProduct.getProductPrise())
                        .productQuantity(outcomeProduct.getQuantity())
                        .organizationProductId(outcomeProduct.getOrganizationProduct().getId())
                        .build());
            }
        });
        return OutcomeResponse.builder()
                .outcomeId(savedOutcome.getId())
                .outcomeProductList(lists)
                .warehouseId(savedOutcome.getWarehouse().getId())
                .build();
    }

    public OutcomeProduct getByOrganizationProduct(Long organizationProductId) {
        logger.log(Level.INFO, "BranchService existsByOrganizationId method called");
        return outcomeProductRepository.findByOrganizationProduct(organizationProductId);
    }

    public ArrayList<OutcomeResponse> getOutcomesResponseByWarehouseId(Long warehouseId) {
        logger.log(Level.INFO, "BranchService existsByOrganizationId method called");
        Warehouse warehouse = warehouseService.getById(warehouseId);
        ArrayList<OutcomeResponse> outcomeResponses = new ArrayList<>();
        outcomeRepository.findAllByWarehouse(warehouse).forEach(outcome -> {
            ArrayList<OutcomeProductResponse> lists = new ArrayList<>();
            outcomeProductRepository.findAllByIncome(outcome).forEach(outcomeProduct -> {
                lists.add(OutcomeProductResponse.builder()
                        .id(outcomeProduct.getId())
                        .productPrice(outcomeProduct.getProductPrise())
                        .productQuantity(outcomeProduct.getQuantity())
                        .organizationProductId(outcomeProduct.getOrganizationProduct().getId())
                        .build());
            });
            outcomeResponses.add(OutcomeResponse.builder()
                    .outcomeId(outcome.getId())
                    .outcomeProductList(lists)
                    .warehouseId(outcome.getWarehouse().getId())
                    .build());
        });
        return outcomeResponses;
    }


}
