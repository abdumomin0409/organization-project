package com.company.organization.payload.income;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncomeDTO {
    private Long warehouseId;
    ArrayList<IncomeProductDTO> incomeProductDTOList;
}
