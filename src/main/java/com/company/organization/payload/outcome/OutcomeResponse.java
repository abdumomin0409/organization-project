package com.company.organization.payload.outcome;

import com.company.organization.payload.BaseDTO;
import com.company.organization.payload.income.IncomeProductResponse;
import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OutcomeResponse implements BaseDTO {
    private Long warehouseId;
    private Long outcomeId;
    ArrayList<OutcomeProductResponse> outcomeProductList;
}
