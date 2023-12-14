package com.company.organization.payload.income;

import com.company.organization.payload.BaseDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncomeProductResponse implements BaseDTO {
    private Long id;
    private Long organizationProductId;
    private Double productPrice;
    private Integer productQuantity;
}
