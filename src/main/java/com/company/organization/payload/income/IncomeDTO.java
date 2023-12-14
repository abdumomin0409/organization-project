package com.company.organization.payload.income;

import com.company.organization.payload.BaseDTO;
import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncomeDTO implements BaseDTO {
    private Long warehouseId;
    ArrayList<IncomeProductDTO> incomeProductDTOList;
}
