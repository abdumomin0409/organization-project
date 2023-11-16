package com.company.organization.payload.outcome;

import com.company.organization.payload.income.IncomeProductDTO;
import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OutcomeDTO {
    private Long warehouseId;
    ArrayList<OutcomeProductDTO> outcomeProductDTOList;
}
