package com.company.organization.payload.income;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncomeProductResponse {
    private Long id;
    private Long organizationProductId;
    private Double productPrice;
    private Integer productQuantity;
}
