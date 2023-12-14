package com.company.organization.payload.income;

import com.company.organization.payload.BaseDTO;
import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncomeResponse implements BaseDTO {
    private Long warehouseId;
    private Long incomeId;
    ArrayList<IncomeProductResponse> incomeProductList;
}
