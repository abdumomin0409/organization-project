package com.company.organization.payload.income;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncomeResponse {
    private Long warehouseId;
    private Long incomeId;
    ArrayList<IncomeProductResponse> incomeProductList;
}
