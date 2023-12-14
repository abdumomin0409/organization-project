package com.company.organization.payload.outcome;

import com.company.organization.payload.BaseDTO;
import com.company.organization.payload.income.IncomeProductDTO;
import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OutcomeDTO implements BaseDTO {
    private Long warehouseId;
    ArrayList<OutcomeProductDTO> outcomeProductDTOList;
}
