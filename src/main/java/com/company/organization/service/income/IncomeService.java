package com.company.organization.service.income;

import com.company.organization.domain.branch.Warehouse;
import com.company.organization.domain.income.Income;
import com.company.organization.domain.income.IncomeProduct;
import com.company.organization.payload.income.IncomeDTO;
import com.company.organization.payload.income.IncomeProductResponse;
import com.company.organization.payload.income.IncomeResponse;
import com.company.organization.repository.income.IncomeProductRepository;
import com.company.organization.repository.income.IncomeRepository;
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
public class IncomeService implements BaseService {
    private final IncomeRepository incomeRepository;
    private final IncomeProductRepository incomeProductRepository;
    private final WarehouseService warehouseService;
    private final OrganizationProductService organizationProductService;
    Logger logger = Logger.getLogger(IncomeService.class.getName());

    public IncomeResponse create(IncomeDTO dto) {
        logger.log(Level.INFO, "BranchService create method called");
        Warehouse warehouse = warehouseService.getById(dto.getWarehouseId());
        Income savedIncome = incomeRepository.save(Income.incomeBuilder()
                .isActive(true)
                .warehouse(warehouse)
                .build());
        ArrayList<IncomeProductResponse> lists = new ArrayList<>();
        dto.getIncomeProductDTOList().forEach(incomeProductDTO -> {
            if (organizationProductService.existsById(incomeProductDTO.getOrganizationProductId())) {
                IncomeProduct incomeProduct = getByOrganizationProduct(incomeProductDTO.getOrganizationProductId());
                if (Objects.nonNull(incomeProduct)) {
                    incomeProduct.setQuantity(incomeProduct.getQuantity() + incomeProductDTO.getProductQuantity());
                    incomeProduct = incomeProductRepository.save(incomeProduct);
                } else {
                    incomeProduct = incomeProductRepository.save(IncomeProduct.builder()
                            .income(savedIncome)
                            .productPrise(incomeProductDTO.getProductPrice())
                            .quantity(incomeProductDTO.getProductQuantity())
                            .organizationProduct(organizationProductService.findById(incomeProductDTO.getOrganizationProductId()))
                            .build());
                }
                lists.add(IncomeProductResponse.builder()
                        .id(incomeProduct.getId())
                        .productPrice(incomeProduct.getProductPrise())
                        .productQuantity(incomeProduct.getQuantity())
                        .organizationProductId(incomeProduct.getOrganizationProduct().getId())
                        .build());
            }
        });
        return IncomeResponse.builder()
                .incomeId(savedIncome.getId())
                .incomeProductList(lists)
                .warehouseId(savedIncome.getWarehouse().getId())
                .build();
    }

    public IncomeProduct getByOrganizationProduct(Long id) {
        logger.log(Level.INFO, "BranchService existsByOrganizationId method called");
        return incomeProductRepository.findByOrganizationProduct(id);
    }

    public ArrayList<IncomeResponse> getIncomesResponseByWarehouseId(Long warehouseId) {
        logger.log(Level.INFO, "BranchService existsByOrganizationId method called");
        Warehouse warehouse = warehouseService.getById(warehouseId);
        ArrayList<IncomeResponse> incomeResponses = new ArrayList<>();
        incomeRepository.findAllByWarehouse(warehouse).forEach(income -> {
            ArrayList<IncomeProductResponse> lists = new ArrayList<>();
            incomeProductRepository.findAllByIncome(income).forEach(incomeProduct -> {
                lists.add(IncomeProductResponse.builder()
                        .id(incomeProduct.getId())
                        .productPrice(incomeProduct.getProductPrise())
                        .productQuantity(incomeProduct.getQuantity())
                        .organizationProductId(incomeProduct.getOrganizationProduct().getId())
                        .build());
            });
            incomeResponses.add(IncomeResponse.builder()
                    .incomeId(income.getId())
                    .incomeProductList(lists)
                    .warehouseId(income.getWarehouse().getId())
                    .build());
        });
        return incomeResponses;
    }


}
